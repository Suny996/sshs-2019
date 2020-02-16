package com.sshs.security.configuration;

import com.sshs.security.authentication.UserDetailsReactiveAuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.HttpStatusReturningServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;

import javax.annotation.Resource;

/**
 * @author Suny
 * @date 2018-11-05
 */
//@Configuration
//@EnableWebFluxSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebFluxSecurityConfig {
    @Resource
    ReactiveUserDetailsService reactiveUserDetailsService;


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .httpBasic().and()//.formLogin().authenticationSuccessHandler(new WebFilterChainServerAuthenticationSuccessHandler());
                .authenticationManager(new UserDetailsReactiveAuthenticationManager(reactiveUserDetailsService)).logout().logoutHandler(new SecurityContextServerLogoutHandler()).logoutSuccessHandler(new HttpStatusReturningServerLogoutSuccessHandler()).and().formLogin();
        return http.build();
    }

}