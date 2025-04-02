package com.spring_concepts.jwt_based_security.controller;

import com.spring_concepts.jwt_based_security.models.User;
import com.spring_concepts.jwt_based_security.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UserRepository userRepository;

    @GetMapping("/all")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> all = userRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
