package com.opentuter.profileservice.config;

import com.opentuter.profileservice.jwt.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDateTime;

//**
// this is securit configuration file .
// using bcrypt for password  hashing and use for jwt for authentication and authorization
// difine JwtAuthFilter
// *
// import org.springframwork.security
// import java.io.IOException
// import java.time.LocalDateTime*/
@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    //**
    // @Bean -> this is an annotation used to tell spring to create and manage an object as a Bean
    // it is usually written inside a @Configuration class
    // */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF (standard for stateless APIs)
                .csrf(csrf -> csrf.disable())

                // Enable CORS (essential if frontend is on a different port)
                .cors(Customizer.withDefaults())

                // 1. Define Access Rules
                .authorizeHttpRequests(auth -> auth
                        // Goal 2: Allow Login & Register without token
                        .requestMatchers("/api/user/reg", "/api/user/login").permitAll()

                        // Allow Swagger UI and OpenAPI docs without authentication
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Catch-all: Anything else requires login (must be last)
                        .anyRequest().authenticated()
                )

                // Make it Stateless (No Session created)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Custom Error Handling (Without ObjectMapper)
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) ->
                                writeCustomJsonError(response, HttpStatus.UNAUTHORIZED, "Unauthorized", authException.getMessage(), request))

                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                writeCustomJsonError(response, HttpStatus.FORBIDDEN, "Forbidden", accessDeniedException.getMessage(), request))
                )

                // Add your JWT Filter before the standard auth filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(login -> login.defaultSuccessUrl("/swagger-ui.html", true));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // --- Helper Methods for Manual JSON Construction ---

    private void writeCustomJsonError(HttpServletResponse response, HttpStatus status, String error, String message, HttpServletRequest request) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // Manually build the JSON string
        String json = String.format(
                "{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                LocalDateTime.now(),
                status.value(),
                escapeJson(error),
                escapeJson(message),
                escapeJson(request.getRequestURI())
        );

        response.getWriter().write(json);
    }

    // Simple helper to escape quotes so JSON doesn't break
    private String escapeJson(String text) {
        if (text == null) return "";
        return text.replace("\"", "\\\"").replace("\n", " ");
    }
}