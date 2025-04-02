package com.spring_concepts.jwt_based_security.controller;

import com.spring_concepts.jwt_based_security.models.AuthenticationRequest;
import com.spring_concepts.jwt_based_security.models.AuthenticationResponse;
import com.spring_concepts.jwt_based_security.models.User;
import com.spring_concepts.jwt_based_security.repositories.UserRepository;
import com.spring_concepts.jwt_based_security.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JWTUtil jwtUtil,
                          UserRepository userRepository ,
                          BCryptPasswordEncoder bCryptPasswordEncoder){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Operation(summary = "New User Registration")
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody User user){
        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if(userExists){
            return ResponseEntity.ok("User already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @Operation(summary = "User login to generate JWT")
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
