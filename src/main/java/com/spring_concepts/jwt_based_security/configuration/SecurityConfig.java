package com.spring_concepts.jwt_based_security.configuration;

import com.spring_concepts.jwt_based_security.filter.JwtFilter;
import com.spring_concepts.jwt_based_security.service.CustomUserDetailsService;
import com.spring_concepts.jwt_based_security.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;


    public SecurityConfig(JWTUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable() //// Disable CSRF (not needed for JWT authentication)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/auth/login").permitAll() //// Allow login API without authentication
                                .anyRequest().authenticated() // // All other requests require authentication
                ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//// Disable session
                .addFilter(new JwtFilter());// Add JWT filter
        return httpSecurity.build();
    }
}
