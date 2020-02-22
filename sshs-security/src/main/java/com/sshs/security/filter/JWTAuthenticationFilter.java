package com.sshs.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshs.core.message.Message;
import com.sshs.security.error.SecurityErrorCode;
import com.sshs.security.util.JwtTokenUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义JWT认证过滤器
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author Suny on 2019/9/13.
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {//BasicAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(jwtTokenUtils.TOKEN_HEADER);
        if (token == null) {
            token = (String) request.getSession().getAttribute(jwtTokenUtils.TOKEN_HEADER);
        }
        if (token != null && StringUtils.startsWith(token, jwtTokenUtils.TOKEN_PREFIX)) {
            token = StringUtils.substring(token, jwtTokenUtils.TOKEN_PREFIX.length());
        } else {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String username = jwtTokenUtils.getUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                /*
                 *  注意：
                 *       这里代码不应该从数据库中去查，而是从缓存中根据token去查，目前只是做测试，无关紧要
                 *      如果是真正的项目实际开发需要增加缓存
                 */
                UserDetails userDetails = jwtTokenUtils.getUserDetails(username);

                if (jwtTokenUtils.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetails(request));


                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        } catch (Exception e) {
            logger.error("身份验证异常", e);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(Message.failure(SecurityErrorCode.AUTHORISE_EXCEPTION)));
            return;
        }

        filterChain.doFilter(request, response);
    }
}