package com.shop.preorder.util;

import com.shop.preorder.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenUtil {

    // Token 생성
    public static String createToken(String email, String key, long expireTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("userName", email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(getKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    // secretKey 바이트 배열 변환
    private static Key getKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // claim return
    public static Claims extractClaims(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getUserName(String token, String key) {
        return extractClaims(token, key).get("userName", String.class);
    }

    // token 만료 여부 체크 (true : 만료, false : 활성)
    public static Boolean isTokenExpired(String token, String key) {
        Date expiration = extractClaims(token, key).getExpiration();
        return expiration.before(new Date());
    }


}
