package com.spring.maple.oauth2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: hyc
 * @time: 2019/11/29 15:38
 */
public class BCryptPasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.matches("123456",encoder.encode("123456")));
        System.out.println(encoder.encode("test1"));
        System.out.println(encoder.encode("test2"));
        System.out.println(encoder.encode("123456"));
        System.out.println(encoder.encode("123456"));
    }
}
