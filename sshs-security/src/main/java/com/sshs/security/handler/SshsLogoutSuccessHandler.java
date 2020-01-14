package com.sshs.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshs.core.aop.SLog;
import com.sshs.core.message.Message;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Suny
 * @date 2019-1-19
 */
@Component
public class SshsLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    @SLog(value = "退出系统", module = "security")
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(Message.success(authentication)));
        out.flush();
        out.close();
    }
}
