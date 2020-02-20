package com.sshs.system.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 注入swagger资源文件
 *
 * @author Suny
 * @date 2018-10-30 19:54
 **/
@Configuration
@EnableSwagger2
//@EnableSwaggerBootstrapUI
//@Profile({"dev","test"})
@ConditionalOnProperty(name = "sshs.swagger.enable", havingValue = "true")
public class SystemSwaggerConfig implements WebMvcConfigurer {
    /**
     * 显示swagger-ui.html文档展示页，还必须注入swagger资源：
     *
     * @param //registry
     */
   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/

    //可以注入多个doket，也就是多个版本的api，可以在看到有三个版本groupName不能是重复的，v1和v2是ant风格匹配，配置文件
    @Bean
    public Docket systemApi() {
        //可以添加多个header或参数
       /* ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("token") //参数名
                .defaultValue("token") //默认值
                .description("header中token字段测试")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());*/
        return new Docket(DocumentationType.SWAGGER_2).groupName("系统管理(SYSTEM)").select().apis(RequestHandlerSelectors.basePackage("com.sshs.system"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo1());//.globalOperationParameters(aParameters);
    }

    private ApiInfo apiInfo1() {
        return new ApiInfoBuilder()
                .title("SSHS-系统管理(SYSTEM)")
                //.termsOfServiceUrl("www.baidu.com").contact("Suny999")
                //.contact(new Contact("Suny", "https://github.com/Suny999", "mail.suny@qq.com"))
                //.version("v0.1")
                .build();
    }
}
