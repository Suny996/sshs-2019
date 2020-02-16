package com.sshs.security.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 *
 * @author Suny on 2019/9/12.
 */
public class JWTLoginFilter extends
        AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    /*
        private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
        private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;*/
    private boolean postOnly = true;
    // ~ Constructors
    // ===================================================================================================

    public JWTLoginFilter(String contextPath) {
        super(new AntPathRequestMatcher(contextPath + "/login", "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String username = null;
        String password = null;
        if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
            JSONObject jsonObject = getJsonParam(request);
            username = jsonObject.getString(SPRING_SECURITY_FORM_USERNAME_KEY);
            password = jsonObject.getString(SPRING_SECURITY_FORM_PASSWORD_KEY);
        } else {
            username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
            password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        }

        if (StringUtils.isBlank(username)) {
            throw new AuthenticationServiceException(
                    "username con't be none ! ");
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request     that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     *                    set
     */
    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


    /**
     * Defines whether only HTTP POST requests will be allowed by this filter. If set to
     * true, and an authentication request is received which is not a POST request, an
     * exception will be raised immediately and authentication will not be attempted. The
     * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
     * authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
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
