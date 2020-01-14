package com.sshs.security.authentication;

import com.sshs.core.message.Message;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * @author Suny
 * @date 2019-02-19
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {
    private PasswordEncoder passwordEncoder;
    private volatile String userNotFoundEncodedPassword;

    public LoginAuthenticationProvider(UserDetailsService userDetailsService) {
        super();
        // 这个地方一定要对userDetailsService赋值，不然userDetailsService是null (这个坑有点深)
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        super.setPasswordEncoder(this.getPasswordEncoder());
    }

    public LoginAuthenticationProvider() {
        setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        super.setPasswordEncoder(this.getPasswordEncoder());
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;

        /*if (this.getSaltSource() != null) {
            salt = this.getSaltSource().getSalt(userDetails);
        }*/

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(
                    messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage("US3003", Message.getMessage("US3003")));
        }
        //我就改了这个地方，增加一个验证码登录标识(具体怎么做就看自己了)
        // 【原本的是登录密码和数据密码不等就抛出异常，我用验证码登录时压根都不知道密码(ー`´ー)】
        //so 我给短信登录时赋值一个默认密码（验证码登录标识）来判断，不让这儿报异常
        /*if (!presentedPassword.equals("YZMLG_KSssdS1D145Sd4S")) {
            if (!getPasswordEncoder().isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
                logger.debug("Authentication failed: password does not match stored value");

                throw new BadCredentialsException(messages
                        .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }*/
    }

    @Override
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
        this.userNotFoundEncodedPassword = null;
    }

    @Override
    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}