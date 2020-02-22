package com.sshs.core.version;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * API版本控制注解
 *
 * @author Suny
 * @date 2019/4/18 11:17.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Version {
    /**
     * 版本号
     * @return
     */
    int value();
}