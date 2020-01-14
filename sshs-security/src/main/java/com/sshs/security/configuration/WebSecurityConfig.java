package com.sshs.security.configuration;

import com.sshs.security.authentication.AuthenticationAccessDeniedHandler;
import com.sshs.security.decision.UrlAccessDecisionManager;
import com.sshs.security.handler.*;
import com.sshs.security.service.SshsSecurityMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
    @Value("${spring.security.maximum-sessions}")
    private int maximumSessions;

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new LoginAuthenticationProvider(scaiUserService));
        auth.userDetailsService(scaiUserService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }*/

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login");
    }*/

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().formLogin().loginPage("/login")
                //.loginProcessingUrl("/login")
                // 登录请求路径
                .successHandler(sshsAuthenticationSuccessHandler)
                // 验证成功处理器
                .failureHandler(sshsAuthenticationFailureHandler)
                // 验证失败处理器
                .and().authorizeRequests()
                .antMatchers("/login", "/ureport/**", "/sreport/**", "/sreport2/**", "/sigma/api/*").permitAll()
                // 登录请求路径不进行过滤
                .anyRequest()
                .authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new SshsLoginUrlAuthenticationEntryPoint())
                //.and().addFilter(new LoginPageGeneratingWebFilter())
                .and().logout().logoutSuccessHandler(sshsLogoutSuccessHandler)
                .and().sessionManagement().maximumSessions(maximumSessions).expiredSessionStrategy(new SshsSessionExpiredStrategy())
                .and().invalidSessionStrategy(new SshsInvalidSessionStrategy()).and().csrf().disable();
        // 取消跨站请求伪造防护
    }
}