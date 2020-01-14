package com.sshs.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshs.core.aop.SLog;
import com.sshs.core.message.Message;
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
        Message message = null;
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            message = Message.failure("US3001");
        } else if (e instanceof LockedException) {
            message = Message.failure("账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            message = Message.failure("密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            message = Message.failure("账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            message = Message.failure("账户被禁用，请联系管理员!");
        } else {
            message = Message.failure("US3003", "登录失败!");
        }
        //resp.setStatus(401);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(message));
        out.flush();
        out.close();
    }
}