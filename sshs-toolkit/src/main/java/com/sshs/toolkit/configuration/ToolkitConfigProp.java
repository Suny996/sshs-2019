package com.sshs.toolkit.configuration;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 */
//@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "toolkit.coder", ignoreUnknownFields = true)
public class ToolkitConfigProp {
    String pathJava;
    String pathView;
    String dbUser;
    Map<String, String> modelMapping;
    Map<String, String> columnTypeMapping;
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("toolkit-config.yml"));//File引入
        //yaml.setResources(new ClassPathResource("application.yml"));//class引入
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
    public String getPathJava() {
        return pathJava;
    }

    public void setPathJava(String pathJava) {
        this.pathJava = pathJava;
    }

    public String getPathView() {
        return pathView;
    }

    public void setPathView(String pathView) {
        this.pathView = pathView;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public Map<String, String> getModelMapping() {
        return modelMapping;
    }

    public void setModelMapping(Map<String, String> modelMapping) {
        this.modelMapping = modelMapping;
    }

    public Map<String, String> getColumnTypeMapping() {
        return columnTypeMapping;
    }

    public void setColumnTypeMapping(Map<String, String> columnTypeMapping) {
        this.columnTypeMapping = columnTypeMapping;
    }
}
