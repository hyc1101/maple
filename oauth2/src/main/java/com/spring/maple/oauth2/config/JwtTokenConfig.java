package com.spring.maple.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class JwtTokenConfig {

    @Bean
    public TokenStore jwtTokenStore() {

        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    // /**
    // * token生成处理：指定签名
    // */
    // @Bean
    // public JwtAccessTokenConverter jwtAccessTokenConverter() {
    //
    // JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
    // accessTokenConverter.setSigningKey("internet_plus");
    // return accessTokenConverter;
    // }

    @Bean
    public TokenEnhancer jwtTokenEnhancer() {

        return new JwtTokenEnhancer();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("cnsesan-jwt.jks"),
            "cnsesan123".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("cnsesan-jwt"));
        return converter;
    }
}
