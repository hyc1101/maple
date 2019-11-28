package com.spring.maple.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableApolloConfig
public class RegistryApplication {

    public static void main(String[] args) {

        SpringApplication.run(RegistryApplication.class, args);
    }

}
