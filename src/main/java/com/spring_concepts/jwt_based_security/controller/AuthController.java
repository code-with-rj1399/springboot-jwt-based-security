package com.spring_concepts.jwt_based_security.controller;

import com.spring_concepts.jwt_based_security.models.AuthenticationRequest;
import com.spring_concepts.jwt_based_security.models.AuthenticationResponse;
import com.spring_concepts.jwt_based_security.repositories.UserRepository;
import com.spring_concepts.jwt_based_security.utils.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserRepository userRepository){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        ));
        String token = jwtUtil.generateToken(authenticationRequest.getUsername()); // Generate JWT token
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
