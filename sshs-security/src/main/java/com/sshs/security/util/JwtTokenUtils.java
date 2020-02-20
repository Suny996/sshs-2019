package com.sshs.security.util;

import com.sshs.core.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class JwtTokenUtils {
    public  final String TOKEN_HEADER = "Authorization";

    public  final String TOKEN_PREFIX = "Bearer ";
    /**
     * 密钥key
     */
    private  final String SECRET = "jwtsecurit";

    /**
     * JWT的发行人
     */
    private  final String ISS = "sshs group";

    /**
     * 自定义用户信息
     */
    private  final String ROLE_CLAIMS = "rol";

    /**
     * 过期时间是3600秒，既是1个小时
     */
    public  final long EXPIRATION = 3600L * 1000;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    public  final long EXPIRATION_REMEMBER = 604800L * 1000;

    @Autowired
    @Qualifier("sshsUserService")
    private UserDetailsService userDetailService;

    /**
     * 创建token
     *
     * @param details      用户角色信息
     * @param isRememberMe 是否记住我
     * @return
     */
    public  String createToken(UserDetails details, boolean isRememberMe) throws BusinessException {
        // 如果选择记住我，则token的过期时间为
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;

        HashMap<String, Object> map = new HashMap<>();

        map.put(ROLE_CLAIMS, details.getAuthorities()); // 角色名字
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET) // 加密算法
                .setClaims(map) // 自定义信息
                .setIssuer(ISS) // jwt发行人
                .setSubject(details.getUsername()) // jwt面向的用户
                .setIssuedAt(new Date()) // jwt发行人
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // key过期时间
                .compact();
    }

    /**
     * 从token获取用户信息
     *
     * @param token
     * @return
     */
    public  String getUsername(String token) throws BusinessException {
        return getTokenBody(token).getSubject();
    }

    /**
     * 从token中获取用户角色
     *
     * @param token
     * @return
     */
    public  Set<String> getUserRole(String token) throws BusinessException {
        List<GrantedAuthority> userAuthorities = (List<GrantedAuthority>) getTokenBody(token).get(ROLE_CLAIMS);
        return AuthorityUtils.authorityListToSet(userAuthorities);
    }

    /**
     * 是否已过期
     *
     * @param token
     * @return
     */
    public  boolean isExpiration(String token) throws BusinessException {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private  Claims getTokenBody(String token) throws BusinessException {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    /**
     * 验证token
     *
     * @param token
     * @param userDetails
     * @return
     */
    public  boolean validateToken(String token, UserDetails userDetails) throws BusinessException {
        User user = (User) userDetails;
        final String username = getUsername(token);
        return (username.equals(user.getUsername()) && isExpiration(token) == false);
    }

    /**
     * 临时缓存方案
     *
     * @param username
     * @return
     */
    @Cacheable(value = "sprint_security_user_details", key = "#username")
    public UserDetails getUserDetails(String username) {
        return userDetailService.loadUserByUsername(username);
    }
}