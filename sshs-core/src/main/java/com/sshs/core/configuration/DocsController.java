package com.sshs.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DocsController {
    @Value("${server.servlet.contextPath:}")
    String contextPath;

    @RequestMapping("/doc")
    public void doc(HttpServletResponse response) throws IOException {
        response.sendRedirect(contextPath + "/doc.html");
    }

    @RequestMapping("/docs")
    public void docs(HttpServletResponse response) throws IOException {
        response.sendRedirect(contextPath + "/swagger-ui.html");
    }
}
