package com.sshs.security.service;

import com.sshs.core.message.Message;
import com.sshs.security.error.SecurityErrorCode;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义身份认证验证组件
 *
 * @author zhaoxinguo on 2017/9/12.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 认证逻辑
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (null != userDetails) {
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                // 生成令牌 这里令牌里面存入了:name,password,authorities, 当然你也可以放其他内容
                return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException(Message.failure(SecurityErrorCode.PASSWORD_IS_WRONG).toString());
            }
        } else {
            throw new UsernameNotFoundException(SecurityErrorCode.USER_NOT_EXISTS.getMsg());
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
