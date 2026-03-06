package com.opentuter.profileservice.jwt;

import com.opentuter.profileservice.service.JwtService;
import com.opentuter.profileservice.util.Utils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

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
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals(Utils.ACCESS_URL_LOGIN)
                || request.getServletPath().equals(Utils.ACCESS_URL_REGISTER);
    }


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Get Authorization header
        String header = request.getHeader(Utils.AUTHORIZATION);

        //implemnt methos for continue request without authentication
        if(header == null || !header.startsWith(Utils.BEARER)){
            filterChain.doFilter(request, response);
            return;
        }

        // method for remove "Bearer" and get only JWT token
        String token = header.substring(Utils.BEGININDEX);

        try {
            //method for extract email from jwt (no role extraction for authorization)
            String email = jwtService.extractEmail(token);

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //*
                // Create authentication with generic USER authority (not role-based)
                // This ensures Spring Security considers the user "fully authenticated"
                // but no role-based authorization checks are performed*/
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(new SimpleGrantedAuthority("USER")) // Generic authority, no role-based authorization
                );

                //implement method for store authentication is securitycontext and continue request processing
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            // Token invalid or expired
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}