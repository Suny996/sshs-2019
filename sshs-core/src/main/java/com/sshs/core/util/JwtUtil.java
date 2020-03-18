package com.sshs.core.util;

import com.sshs.core.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class JwtUtil {
    public static final String X_ACCESS_TOKEN = "X-Access-Token";
    public static final String TOKEN_PREFIX = "Bearer ";
    /**
     * 密钥key
     */
    private static final String SECRET = "jwtsecurit";

    /**
     * JWT的发行人
     */
    private static final String ISS = "sshs group";

    /**
     * 自定义用户信息
     */
    private static final String ROLE_CLAIMS = "rol";

    /**
     * 过期时间是3600秒，既是1个小时
     */
    public static final long EXPIRATION = 3600L * 1000;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    public static final long EXPIRATION_REMEMBER = 604800L * 1000;

    /**
     * 创建token
     *
     * @param username     用户角色信息
     * @param isRememberMe 是否记住我
     * @return
     */
    public static String createToken(String username, List<String> roles, boolean isRememberMe) throws BusinessException {
        // 如果选择记住我，则token的过期时间为
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;

        HashMap<String, Object> map = new HashMap<>();

        map.put(ROLE_CLAIMS, roles); // 角色名字
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, SECRET) // 加密算法
                .setClaims(map) // 自定义信息
                .setIssuer(ISS) // jwt发行人
                .setSubject(username) // jwt面向的用户
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
    public static String getUsername(String token) throws BusinessException {
        return getTokenBody(token).getSubject();
    }

    /**
     * 是否已过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) throws BusinessException {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) throws BusinessException {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    /**
     * 验证token
     *
     * @param token
     * @param username
     * @return
     */
    public static boolean validateToken(String token, String username) throws BusinessException {
        final String tmpUser = getUsername(token);
        return (tmpUser.equals(username) && isExpiration(token) == false);
    }
}