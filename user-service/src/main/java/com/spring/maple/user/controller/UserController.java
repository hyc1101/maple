package com.spring.maple.user.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maple.cat.annotation.CatAnnotation;
import com.spring.maple.user.domain.User;
import com.spring.maple.user.repository.UserRepository;

/**
 * @author hyc
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    // @CatAnnotation
    // public User findById(@PathVariable Long id) {
    //
    // User result = userRepository.findById(id).orElse(new User());
    // return result;
    // }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @CatAnnotation
    public User findByName(@PathVariable String username) {

        User user = new User();
        user.setUsername(username);
        User result = this.userRepository.findOne(Example.of(user)).orElse(new User());
        return result;
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    @CatAnnotation
    public void createUser(@RequestParam("username") String username, @RequestParam("password") String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword("{bcrypt}"+encoder.encode(password));
        user.setRealname(username);
        user.setAge((int)(System.currentTimeMillis() % 30 + 18));
        user.setBalance(new BigDecimal("180"));
        this.userRepository.save(user);
    }
}
