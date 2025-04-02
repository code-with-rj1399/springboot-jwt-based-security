package com.spring_concepts.jwt_based_security.models;

import lombok.*;

public class AuthenticationResponse {
    private String token; // This holds the JWT token


    public AuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}