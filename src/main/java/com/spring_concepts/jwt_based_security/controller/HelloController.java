package com.spring_concepts.jwt_based_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("user")
    public String helloUser(){
        return "Hello User";
    }

    @GetMapping("admin")
    public String helloAdmin(){
        return "Hello Admin";
    }
}
