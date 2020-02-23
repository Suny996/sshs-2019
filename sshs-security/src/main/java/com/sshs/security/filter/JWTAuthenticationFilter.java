package com.sshs.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshs.core.base.model.GlobalUser;
import com.sshs.core.constant.Global;
import com.sshs.core.message.Message;
import com.sshs.security.error.SecurityErrorCode;
import com.sshs.security.model.Privilege;
import com.sshs.security.model.SecurityUser;
import com.sshs.security.model.SshsGrantedAuthority;
import com.sshs.security.util.JwtTokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 *
 * @author Suny on 2019/9/12.
 */
public class JWTAuthenticationFilter extends
        UsernamePasswordAuthenticationFilter {
    private RequestCache requestCache = new HttpSessionRequestCache();
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Value("${sshs.swagger.enable:true}")
    Boolean swaggerEnable;

    public JWTAuthenticationFilter(String loginUrl, AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        // super.setFilterProcessesUrl(loginUrl);
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(
                loginUrl, "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String username;
        String password;
        if (request.getContentType() != null && request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            JSONObject jsonObject = getJsonParam(request);
            username = jsonObject.getString(SPRING_SECURITY_FORM_USERNAME_KEY);
            password = jsonObject.getString(SPRING_SECURITY_FORM_PASSWORD_KEY);
        } else {
            username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
            password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        }

        if (StringUtils.isBlank(username)) {
            throw new AuthenticationServiceException(
                    SecurityErrorCode.USERNAME_CAN_NOT_NULL.getMsg());
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {

        GlobalUser gu = new GlobalUser();
        SecurityUser su = (SecurityUser) authentication.getPrincipal();
        gu.setUserCode(su.getUsername());
        gu.setOrgCode(su.getOrgCode());
        gu.setUserName(su.getUserNameCn());
        gu.setIpAddr(request.getRemoteAddr());
        SshsGrantedAuthority sga = (SshsGrantedAuthority) authentication.getAuthorities().toArray()[0];
        List<String> roles = new ArrayList<>();
        for (Privilege p : sga.getRoles()) {
            roles.add(p.getId());
        }
        gu.setRoleCodes(roles);
        gu.setMenus(sga.getMenus());
        gu.setButtons(sga.getButtons());
        request.getSession().setAttribute(Global.USER, gu);


        //String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
        final String token = jwtTokenUtils.TOKEN_PREFIX + jwtTokenUtils.createToken((UserDetails) authentication.getPrincipal(), true);
        response.addHeader("Set-" + Global.TOKEN_HEADER, token);
        request.getSession().setAttribute(Global.TOKEN_HEADER, token);

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest == null) {
            if (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(request.getContentType()) && swaggerEnable) {
                new DefaultRedirectStrategy().sendRedirect(request, response, "/docs");
            } else {
                response.setContentType("application/json;charset=utf-8");
                //登录成功
                ObjectMapper om = new ObjectMapper();
                PrintWriter out = response.getWriter();
                out.write(om.writeValueAsString(Message.success(gu)));
                out.flush();
                out.close();
                return;
            }
        } else {
            //clearAuthenticationAttributes(request);
            String targetUrl = savedRequest.getRedirectUrl();
            logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
            new DefaultRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }


    /**
     * 获取Json数据
     *
     * @param request
     * @return
     */
    private JSONObject getJsonParam(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        ServletInputStream inputStream = null;
        try {
            int contentLength = request.getContentLength();
            if (!(contentLength < 0)) {
                byte[] buffer = new byte[contentLength];
                inputStream = request.getInputStream();
                for (int i = 0; i < contentLength; ) {
                    int len = inputStream.read(buffer, i, contentLength);
                    if (len == -1) {
                        break;
                    }
                    i += len;
                }
                String jsonParam = new String(buffer, "utf-8");
                jsonObject = JSONObject.parseObject(jsonParam);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
}
