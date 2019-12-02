package com.spring.maple.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_IDS = "v1";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        // 配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory()

            // // client模式
            // .withClient("client_1").resourceIds(RESOURCE_IDS)
            // .authorizedGrantTypes("client_credentials",
            // "refresh_token").scopes("select").authorities("oauth2")
            // .secret(finalSecret).accessTokenValiditySeconds(3600).and()
            .withClient("browser1").authorizedGrantTypes("password", "authorization_code", "refresh_token")
            .scopes("all").authorities("oauth2").redirectUris("http://www.baidu.com").secret(finalSecret)
            .accessTokenValiditySeconds(3600).and()
            // 密码模式
            .withClient("browser").authorizedGrantTypes("password", "refresh_token").scopes("all").authorities("oauth2")
            .secret(finalSecret).accessTokenValiditySeconds(3600);
    }

    /**
     * 认证服务端点配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        endpoints
            // // 用户管理
//             .userDetailsService(userDetailsService)
            // token生成方式
            .tokenStore(tokenStore)
            // 启用oauth2管理
            .authenticationManager(authenticationManager)
            // 接收GET和POST
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

        // 正常模式
//        endpoints.accessTokenConverter(jwtAccessTokenConverter);
        // JWT扩展
         TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
         List<TokenEnhancer> enhancerList = new ArrayList<>();
         enhancerList.add(jwtTokenEnhancer);
         enhancerList.add(jwtAccessTokenConverter);
         enhancerChain.setTokenEnhancers(enhancerList);
         endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {

        oauthServer.allowFormAuthenticationForClients()
            // 开启/oauth/token_key验证端口无权限访问
            .tokenKeyAccess("permitAll()")
            // 开启/oauth/check_token验证端口认证权限访问
            .checkTokenAccess("isAuthenticated()");
    }

}
