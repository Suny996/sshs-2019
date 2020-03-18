package com.sshs;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.request.RequestContextListener;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Suny
 * @date 2018-10-28
 */

@MapperScan(basePackages = {"com.sshs.*.*.mapper"})
@SpringBootApplication
@EnableCaching
@EnableAsync
//@EnableEurekaClient
//@EnableDiscoveryClient
public class SshsApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(SshsApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(SshsApplication.class, args);

        Environment env = application.getEnvironment();
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        logger.info("\n----------------------------------------------------------\n\t" +
                "Application SSHS is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Swagger-ui: http://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }

    /**
     * 重写configure
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SshsApplication.class);
    }
/*
    static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(SshsApplication.class);
    }*/

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}