package com.example.habitgoservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    
    // 秘钥（应从配置文件中读取，这里简化处理）
    private String secret = "habitgo_jwt_secret_key";
    
    // token有效期（24小时）
    private long expiration = 24 * 60 * 60 * 1000;
    
    // 从token中提取用户ID
    public Integer extractUserId(String token) {
        return Integer.parseInt(extractClaim(token, Claims::getSubject));
    }
    
    // 获取token过期时间
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    // 从token中提取特定的声明
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    // 解析token获取所有声明
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    
    // 检查token是否过期
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    // 生成token
    public String generateToken(Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userId.toString());
    }
    
    // 创建token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    
    // 验证token
    public Boolean validateToken(String token, Integer userId) {
        final Integer extractedUserId = extractUserId(token);
        return (extractedUserId.equals(userId) && !isTokenExpired(token));
    }
} 