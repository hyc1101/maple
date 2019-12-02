//package com.spring.maple.oauth2.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
///**
// * @author hyc
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        // http.formLogin() // 登记界面，默认是permit All
//        // .and().authorizeRequests().antMatchers("/", "/home").permitAll() //
//        // 不用身份认证可以访问
//        // .and().authorizeRequests().anyRequest().authenticated() // 其它的请求要求必须有身份认证
//        // .and().csrf() // 防止CSRF（跨站请求伪造）配置
//        //
//        // .requireCsrfProtectionMatcher(new
//        // AntPathRequestMatcher("/oauth/authorize")).disable();
//        // http.formLogin().permitAll().successForwardUrl("/test").and().csrf()//
//        // 防止CSRF（跨站请求伪造）配置
//        // .disable().exceptionHandling()
//        // .authenticationEntryPoint(
//        // (request, response, authException) ->
//        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//        // .and().authorizeRequests().antMatchers("/**").authenticated().and().httpBasic();
//        // http // 配置登陆页/login并允许访问
//        // .formLogin().permitAll().successForwardUrl("/index")
//        // // 登出页
//        // .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
//        // // 其余所有请求全部需要鉴权认证
//        //// .and().authorizeRequests().anyRequest().authenticated()
//        // // 由于使用的是JWT，我们这里不需要csrf
//        // .and().csrf().disable();
//        // super.configure(http);
////        http.formLogin().permitAll().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable().httpBasic();
//    }
//
//    private static final String RESOURCE_IDS = "v2";
//    //
//    // @Override
//    // public void configure(ResourceServerSecurityConfigurer resources) {
//    //
//    // resources.resourceId(RESOURCE_IDS).stateless(true);
//    // }
//
//    // @Override
//    // public void configure(HttpSecurity httpSecurity) throws Exception {
//    //
//    // httpSecurity.authorizeRequests().antMatchers("/v2/**").authenticated(); //
//    // 配置order访问控制，必须认证过后才可以访问
//    //
//    // }
//}
