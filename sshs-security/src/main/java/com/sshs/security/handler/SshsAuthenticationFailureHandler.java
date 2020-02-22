package com.sshs.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshs.core.aop.SLog;
import com.sshs.core.message.Message;
import com.sshs.security.error.SecurityErrorCode;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败后处理
 *
 * @author Suny
 * @date 2019-1-19
 */
@Component
public class SshsAuthenticationFailureHandler
        implements AuthenticationFailureHandler {
    @Override
    @SLog(value = "登录系统失败", module = "security")
    public void onAuthenticationFailure(HttpServletRequest req,
                                        HttpServletResponse resp,
                                        AuthenticationException e) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        Message message;
        if (e instanceof BadCredentialsException) {
            message = Message.failure(SecurityErrorCode.AUTHORISE_FAILURE);
        } else if (e instanceof UsernameNotFoundException) {
            message = Message.failure(SecurityErrorCode.USER_NOT_EXISTS);
        } else if (e instanceof LockedException) {
            message = Message.failure(SecurityErrorCode.ACCOUNT_IS_LOCKED);
        } else if (e instanceof CredentialsExpiredException) {
            message = Message.failure(SecurityErrorCode.PASSWORD_IS_EXPIRED);
        } else if (e instanceof AccountExpiredException) {
            message = Message.failure(SecurityErrorCode.ACCOUNT_IS_EXPIRED);
        } else if (e instanceof DisabledException) {
            message = Message.failure(SecurityErrorCode.ACCOUNT_IS_DISABLED);
        } else {
            message = Message.failure(SecurityErrorCode.AUTHORISE_EXCEPTION);
        }
        resp.setStatus(401);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(message));
        out.flush();
        out.close();
    }
}