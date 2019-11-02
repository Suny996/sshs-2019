package com.sshs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Suny
 * @date 2018-10-28
 */
@MapperScan(basePackages = {"com.sshs.*.*.mapper"})
@SpringBootApplication
@Configuration
@EnableCaching
@EnableAsync
//@EnableEurekaClient
//@EnableDiscoveryClient
public class SshsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SshsApplication.class, args);
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

    static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(SshsApplication.class);
    }
}