package com.spring_concepts.jwt_based_security.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token; // This holds the JWT token
}