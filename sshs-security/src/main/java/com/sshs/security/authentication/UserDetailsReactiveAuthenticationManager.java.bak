package com.sshs.security.authentication;

import com.sshs.core.exception.BusinessException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author Suny
 * @date 2018-11-29
 */
public class UserDetailsReactiveAuthenticationManager implements ReactiveAuthenticationManager {
    private final ReactiveUserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private Scheduler scheduler = Schedulers.parallel();

    public UserDetailsReactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService) {
        Assert.notNull(userDetailsService, "userDetailsService cannot be null");
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        final String username = authentication.getName();
        return this.userDetailsService.findByUsername(username)
                .publishOn(this.scheduler)
                .filter(u -> this.passwordEncoder.matches((String) authentication.getCredentials(), u.getPassword()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BusinessException("-010002"))))
                 .map(u -> new UsernamePasswordAuthenticationToken(u, u.getPassword(), u.getAuthorities()));
    }

    /**
     * The {@link PasswordEncoder} that is used for validating the password. The default is
     * {@link PasswordEncoderFactories#createDelegatingPasswordEncoder()}
     *
     * @param passwordEncoder the {@link PasswordEncoder} to use. Cannot be null
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Sets the {@link Scheduler} used by the {@link UserDetailsRepositoryReactiveAuthenticationManager}.
     * The default is {@code Schedulers.parallel()} because modern password encoding is
     * a CPU intensive task that is non blocking. This means validation is bounded by the
     * number of CPUs. Some applications may want to customize the {@link Scheduler}. For
     * example, if users are stuck using the insecure {@link org.springframework.security.crypto.password.NoOpPasswordEncoder}
     * they might want to leverage {@code Schedulers.immediate()}.
     *
     * @param scheduler the {@link Scheduler} to use. Cannot be null.
     * @since 5.0.6
     */
    public void setScheduler(Scheduler scheduler) {
        Assert.notNull(scheduler, "scheduler cannot be null");
        this.scheduler = scheduler;
    }
}
