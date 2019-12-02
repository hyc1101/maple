package com.spring.maple.oauth2.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.maple.cat.annotation.CatAnnotation;
import com.spring.maple.user.domain.User;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping(value = "v2/{username}")
    @CatAnnotation
    User findByName(@PathVariable("username") String username);

}
