package com.sneaker.backend.utils;

import com.sneaker.backend.entities.UserEntity;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    public String generateToken(UserEntity user){
        return Jwts.builder()
                .setSubject(user.getEmail())                  // WHO is this token for?
                .setIssuedAt(new Date())            // WHEN was it created?
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))    //10 hours
                .signWith(getSecretKey())        // SIGN it so nobody can fake it
                .compact();             // convert to string
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }
        catch(JwtException e){
            log.info(e.getMessage());
            return false;
        }
    }


    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}


