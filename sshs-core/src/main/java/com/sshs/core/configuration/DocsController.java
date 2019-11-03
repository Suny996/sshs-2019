package com.sshs.core.configuration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class DocsController {
    @RequestMapping("/doc")
    public void doc(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @RequestMapping("/docs")
    public void docs(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
