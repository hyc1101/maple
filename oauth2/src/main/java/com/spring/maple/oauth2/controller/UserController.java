package com.spring.maple.oauth2.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MrBird
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal user) {

        return user;
    }

    @GetMapping("/test")
    public String test() {

        return "test";
    }
}
