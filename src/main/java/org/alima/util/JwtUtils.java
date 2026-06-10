package org.alima.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final String expiration ;

    private final SecretKey key;

    public JwtUtils(@Value("${app.jwt.secret}")  String secret,  @Value("${app.jwt.expiration}")  String expiration) {
        this.expiration = expiration;
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }



    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration))) // 24 ساعت اعتبار
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
}

