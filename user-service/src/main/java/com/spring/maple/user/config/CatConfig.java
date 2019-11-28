package com.spring.maple.user.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.maple.cat.annotation.CatAopService;
import com.maple.cat.cathelper.CatServletFilter;

/**
 * @author: hyc
 * @time: 2019/11/28 15:28
 */
@Configuration
@EnableAspectJAutoProxy
public class CatConfig {

    @Bean
    public FilterRegistrationBean catFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        CatServletFilter filter = new CatServletFilter();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("cat-filter");
        registration.setOrder(1);
        return registration;
    }

//    @ConditionalOnMissingBean
    @Bean
    public CatAopService catAopService() {

        return new CatAopService();
    }
}
