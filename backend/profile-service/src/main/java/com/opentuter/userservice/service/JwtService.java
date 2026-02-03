package com.opentuter.userservice.service;

import com.opentuter.userservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm; // Imported this
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key; // Fixed: Added semicolon here
import java.util.Date;

@Component
public class JwtService {

    // implement the secret key use for generate the jwt token
    private static final String secret = "my_super_secure_jwt_secret_key_which_is_long_enough_256bits";

    // implement  function for generate signing key from secret string
    private Key getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    //*
    // implement function for generate jwt token for each user
    // generate this token include in all user details in the token and token expire within one day*/
    public String generateToken(User user)
    {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role" , user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getKey(), SignatureAlgorithm.HS256) // Fixed: Capitalized SignatureAlgorithm
                .compact();
    }

    //implement function for extract subject from jwt
    public String extractEmail(String token)
    {
        return extractAllClaims(token).getSubject();
    }

    //implement function for extract role claim from jet
    public String extractRole(String token) // Fixed: Changed 'toke' to 'token'
    {
        return extractAllClaims(token).get("role", String.class);
    }

    //implement function for parse and validate jwt and return
    private Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}