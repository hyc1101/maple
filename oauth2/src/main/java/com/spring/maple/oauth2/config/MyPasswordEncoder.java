package com.spring.maple.oauth2.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Authod hyc
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {

        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {

        return s.equals(charSequence.toString());
    }
}
