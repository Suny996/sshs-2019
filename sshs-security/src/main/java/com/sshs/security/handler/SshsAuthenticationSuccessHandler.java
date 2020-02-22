package com.sshs.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshs.core.aop.SLog;
import com.sshs.core.base.model.GlobalUser;
import com.sshs.core.constant.Global;
import com.sshs.core.message.Message;
import com.sshs.security.model.Privilege;
import com.sshs.security.model.SecurityUser;
import com.sshs.security.model.SshsGrantedAuthority;
import com.sshs.security.util.JwtTokenUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录成功后处理
 *
 * @author Suny
 * @date 2019-1-19
 */
@Component
public class SshsAuthenticationSuccessHandler extends
        SimpleUrlAuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Value("${sshs.swagger.enable:true}")
    Boolean swaggerEnable;

    @Override
    @SLog(value = "登录系统成功", module = "security")
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {

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

        final String token = jwtTokenUtils.TOKEN_PREFIX + jwtTokenUtils.createToken((UserDetails) authentication.getPrincipal(), true);
        response.addHeader(Global.TOKEN_HEADER, token);
        request.getSession().setAttribute(Global.TOKEN_HEADER, token);

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest == null) {
            if (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(request.getContentType()) && swaggerEnable) {
                getRedirectStrategy().sendRedirect(request, response, "/docs");
                return;
            }
            response.setContentType("application/json;charset=utf-8");
            //登录成功
            ObjectMapper om = new ObjectMapper();
            PrintWriter out = response.getWriter();
            out.write(om.writeValueAsString(Message.success(gu)));
            out.flush();
            out.close();
            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request
                .getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }

        clearAuthenticationAttributes(request);

        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}