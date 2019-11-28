package com.spring.maple.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

/**
 * https://www.cnblogs.com/niechen/p/11672630.html
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableApolloConfig
public class GatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class, args);
    }

}
