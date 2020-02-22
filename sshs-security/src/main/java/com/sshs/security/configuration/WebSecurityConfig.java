package com.sshs.security.configuration;

import com.sshs.security.authentication.AuthenticationAccessDeniedHandler;
import com.sshs.security.decision.UrlAccessDecisionManager;
import com.sshs.security.filter.JWTAuthenticationFilter;
import com.sshs.security.filter.JWTLoginFilter;
import com.sshs.security.handler.SshsAuthenticationFailureHandler;
import com.sshs.security.handler.SshsAuthenticationSuccessHandler;
import com.sshs.security.handler.SshsLoginUrlAuthenticationEntryPoint;
import com.sshs.security.handler.SshsLogoutSuccessHandler;
import com.sshs.security.service.CustomAuthenticationProvider;
import com.sshs.security.service.SshsSecurityMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author Suny
 * @date 2018/12/28.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService sshsUserService;
    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    SshsAuthenticationSuccessHandler sshsAuthenticationSuccessHandler;
    @Resource
    SshsAuthenticationFailureHandler sshsAuthenticationFailureHandler;
    @Resource
    SshsLogoutSuccessHandler sshsLogoutSuccessHandler;
    @Autowired
    SshsSecurityMetadataSourceService metadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;
    @Value("${spring.security.maximumSessions}")
    private int maximumSessions;
    @Value("${server.servlet.contextPath:}")
    String contextPath;
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new LoginAuthenticationProvider(scaiUserService));
        auth.userDetailsService(scaiUserService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }*/

    /**
     * 需要放行的URL
     */
    private String[] getAuthList() {
        // -- register url
        String[] list = {
                (contextPath == null ? "" : contextPath) + "/docs",
                (contextPath == null ? "" : contextPath) + "/doc",
                (contextPath == null ? "" : contextPath) + "/login",
                // -- swagger ui
                (contextPath == null ? "" : contextPath) + "/v2/api-docs",
                (contextPath == null ? "" : contextPath) + "/swagger-resources",
                (contextPath == null ? "" : contextPath) + "/swagger-resources/**",
                (contextPath == null ? "" : contextPath) + "/configuration/ui",
                (contextPath == null ? "" : contextPath) + "/configuration/security",
                (contextPath == null ? "" : contextPath) + "/swagger-ui.html",
                (contextPath == null ? "" : contextPath) + "/webjars/**",
                (contextPath == null ? "" : contextPath) + "/images/**"};
        return list;
    }

    ;

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/index.html", "/static/**", "/login1", contextPath + "/login");
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }*/

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().formLogin().successHandler(sshsAuthenticationSuccessHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest().authenticated()  // 所有请求需要身份认证
                .antMatchers(getAuthList()).permitAll()
                .and().exceptionHandling().authenticationEntryPoint(new SshsLoginUrlAuthenticationEntryPoint())
                .and()
                .exceptionHandling()
                // .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Basic realm=\"MyApp\""))
                .and()
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler) // 自定义访问失败处理器
//                .and()
                .addFilterAt(jWTLoginFilter(), UsernamePasswordAuthenticationFilter.class)//用户名密码登录
                .addFilterBefore(jWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout() // 默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")// 设置注销成功后跳转页面，默认是跳转到登录页面;
//                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll();
       /* http.headers().frameOptions().disable().and().formLogin().loginPage(contextPath + "/login")
                .loginProcessingUrl(contextPath + "/login")
                // 登录请求路径
                .successHandler(sshsAuthenticationSuccessHandler)
                // 验证成功处理器
                .failureHandler(sshsAuthenticationFailureHandler)
                // 验证失败处理器
                .and().authorizeRequests()
                .antMatchers("/login", contextPath + "/login", "/ureport/**", "/sreport/**", "/sreport2/**", "/sigma/api/*").permitAll()
                // 登录请求路径不进行过滤
                .anyRequest()
                .authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new SshsLoginUrlAuthenticationEntryPoint())
                //.and().addFilter(new LoginPageGeneratingWebFilter())
                .and().logout().logoutSuccessHandler(sshsLogoutSuccessHandler).invalidateHttpSession(true)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).maximumSessions(maximumSessions).expiredSessionStrategy(new SshsSessionExpiredStrategy())
                .and().invalidSessionStrategy(new SshsInvalidSessionStrategy()).and().csrf().disable();
        // 取消跨站请求伪造防护
        // 禁用缓存
        http.headers().cacheControl();

        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);*/
    }

    // 该方法是登录的时候会进入
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider(sshsUserService));
    }

    @Bean
    JWTLoginFilter jWTLoginFilter() throws Exception {
        JWTLoginFilter filter = new JWTLoginFilter(contextPath);
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(sshsAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(sshsAuthenticationFailureHandler);
        return filter;
    }

    @Bean
    JWTAuthenticationFilter jWTAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter();
        return filter;
    }
}