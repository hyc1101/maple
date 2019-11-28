package com.spring.maple.user.controller;

import com.maple.cat.annotation.CatAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.maple.user.domain.User;
import com.spring.maple.user.repository.UserRepository;

/**
 * @author hyc
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CatAnnotation
    public User findById(@PathVariable Long id) {

        User result = userRepository.findById(id).orElse(new User());
        return result;
    }
}
