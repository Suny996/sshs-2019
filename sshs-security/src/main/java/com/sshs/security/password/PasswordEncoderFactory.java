package com.sshs.security.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Suny
 */
public class PasswordEncoderFactory {

    /**
     * Creates a {@link DelegatingPasswordEncoder} with default mappings. Additional
     * mappings may be added and the encoding will be updated to conform with best
     * practices. However, due to the nature of {@link DelegatingPasswordEncoder}
     * the updates should not impact users. The mappings current are:
     *
     * <ul>
     * <li>bcrypt - {@link BCryptPasswordEncoder} (Also used for encoding)</li>
     * <li>ldap - {@link LdapShaPasswordEncoder}</li>
     * <li>MD4 - {@link Md4PasswordEncoder}</li>
     * <li>MD5 - {@code new MessageDigestPasswordEncoder("MD5")}</li>
     * <li>noop - {@link NoOpPasswordEncoder}</li>
     * <li>pbkdf2 - {@link Pbkdf2PasswordEncoder}</li>
     * <li>scrypt - {@link SCryptPasswordEncoder}</li>
     * <li>SHA-1 - {@code new MessageDigestPasswordEncoder("SHA-1")}</li>
     * <li>SHA-256 - {@code new MessageDigestPasswordEncoder("SHA-256")}</li>
     * <li>sha256 - {@link StandardPasswordEncoder}</li>
     * </ul>
     *
     * @return the {@link PasswordEncoder} to use
     */
    public static PasswordEncoder createDelegatingPasswordEncoder(String encodingId) {
        if (StringUtils.isEmpty(encodingId)) {
            encodingId = "bcrypt";
        }
        Map<String, PasswordEncoder> encoders = new HashMap<>(20);
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        //encoders.put("ldap", new LdapShaPasswordEncoder());
        //encoders.put("MD4", new Md4PasswordEncoder());
        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
        //encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        //encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
        //encoders.put("sha256", new StandardPasswordEncoder());

        return encoders.get(encodingId);
    }

    public static void main(String[] args) {
        String password = "123456";
        PasswordEncoder passwordEncoder = PasswordEncoderFactory.createDelegatingPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        System.out.println("bcrypt加密后的密码:" + encode);
        System.out.println("bcrypt密码对比:" + passwordEncoder.matches(password, encode));
        passwordEncoder = PasswordEncoderFactory.createDelegatingPasswordEncoder("MD5");
        encode = passwordEncoder.encode(password);
        System.out.println("MD5加密后的密码:" + encode);
        System.out.println("MD5密码对比:" + passwordEncoder.matches(password, encode));
    }

    public static PasswordEncoder createDelegatingPasswordEncoder() {
        return createDelegatingPasswordEncoder("bcrypt");
    }

    public static String encode(String password) {
        return "{bcrypt}" + PasswordEncoderFactory.createDelegatingPasswordEncoder().encode(password);
    }

    public static Boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword != null && encodedPassword.startsWith("{bcrypt}")) {
            encodedPassword = encodedPassword.substring(8);
        }
        return PasswordEncoderFactory.createDelegatingPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}
