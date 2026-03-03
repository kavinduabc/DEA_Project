package com.opentuter.profileservice.service;

import com.opentuter.profileservice.model.User;
import com.opentuter.profileservice.util.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
//**
// In this file implement the secret key and
// implement methods for generate jwt token
//*/

@Component
public class JwtService {

    @Value(Utils.SECRET_KEY)
    private String secret;

    private Key getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //**
    // This method for generate jwt token
    //   -- set all user details  and include issued time  and set expirations after 7 days */
    public String generateToken(User user)
    {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim(Utils.ROLE_S, user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Utils.tokenExpirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token)
    {

        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token)
    {
        return extractAllClaims(token).get(Utils.ROLE_S, String.class);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
