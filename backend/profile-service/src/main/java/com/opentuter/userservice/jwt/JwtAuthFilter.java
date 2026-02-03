package com.opentuter.userservice.jwt;


import com.opentuter.userservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyles.header;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {


    private final JwtService jwtService;


    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    //*
    // implement funtcion for spring security which requests to SKIP filtering
    // Authentication end points*/
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
    {
        return request.getServletPath().startsWith("/api/auth");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException{
        //implemnt methos for continue request without authentication
        if(header == null || !header.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        // method for remove "Bearer" and get only JWT token
        String token = header.substring(7);

        //method for extract email and role from jwt
        String email = jwtService.extractEmail(token);
        String role = jwtService.extractRole(token);

        //implement method for convert role into spring security authority
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

        //*
        // implement method for create authentication object
        // according to that user is now authenticated*/
        EmailPasswordAuthenticationToken auth = new EmailPasswordAuthenticationToken(
                email,
                null,
                authorities
        );

        //implement method for store authentication is securitycontext and continue request processing
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);

    }
}
