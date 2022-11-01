package com.example.mindmap.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@Component
@ConditionalOnProperty("jwt_secret")
public class JwtUtils {
    /**
     * 注入JWT加密密钥
     */
    @Value("${jwt_secret}")
    private String secret;

    /**
     * 定义系统标识头常量
     */
    private static final String HEADER_SYSTEM_KEY = "systemKeyHeader";

    /**
     * 根据用户ID生成JWT
     * @param uid       用户ID
     * @return JWT
     */
    public String generateToken(Integer uid) {
        System.out.println(secret);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(uid.toString())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();

    }

    /**
     * 解析JWT返回用户ID
     *
     * @param token     JWT
     * @return 用户ID
     */
    public Integer parseToken(String token) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            // OK, we can trust this JWT
            return Integer.parseInt(claimsJws.getBody().getSubject());

        } catch (JwtException e) {
            log.info("JWT解析失败:{}", token);
            // don't trust the JWT!
        }
        return null;
    }
}
