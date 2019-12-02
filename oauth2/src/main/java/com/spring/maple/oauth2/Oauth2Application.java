package com.spring.maple.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// @EnableResourceServer  //注释意味着您的服务（就OAuth 2.0而言 - 资源服务器）需要访问令牌才能处理请求。在调用资源服务器之前，应通过OAuth 2.0客户端从授权服务器获取访问令牌
@EnableDiscoveryClient
@EnableFeignClients // 要使用Feign，需要加上此注解，且Feign已默认整合了Hystrix
@EnableCircuitBreaker
public class Oauth2Application {

    public static void main(String[] args) {

        SpringApplication.run(Oauth2Application.class, args);
    }

}
