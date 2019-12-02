//package com.spring.maple.oauth2.config;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//
//import com.spring.maple.oauth2.service.UserServiceDetail;
//
//@Configuration
//@EnableAuthorizationServer // 开启授权服务功能
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//    @Autowired
//    @Qualifier("authenticationManagerBean")
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private UserServiceDetail userServiceDetail;
//
//    // 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//        clients.inMemory().withClient("browser") // 创建了一个client名为browser的客户端
//            .authorizedGrantTypes("refresh_token", "password")// 配置验证类型
//            .secret("123456").scopes("ui")// 配置客户端域为“ui”
//            .and().withClient("account-service")// 创建一个客户端 名字是user-service
//            .secret("123456").scopes("service").authorizedGrantTypes("refresh_token", "password")
//            .accessTokenValiditySeconds(3600);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//
//        // JwtAccessTokenConverter是用来生成token的转换器，而token令牌默认是有签名的，且资源服务器需要验证这个签名。此处的加密及验签包括两种方式：
//        // 对称加密、非对称加密（公钥密钥）
//        // 对称加密需要授权服务器和资源服务器存储同一key值，而非对称加密可使用密钥加密，暴露公钥给资源服务器验签，本文中使用非对称加密方式，配置于AuthorizationServerConfigurerAdapter如下：
//        // reuseRefreshTokens设置为false时，每次通过refresh_token获得access_token时，也会刷新refresh_token；也就是说，会返回全新的access_token与refresh_token。
//        // 默认值是true，只返回新的access_token，refresh_token不变。
//        endpoints.tokenStore(tokenStore())// Token的存储方式为内存
//            .tokenEnhancer(jwtTokenEnhancer()).accessTokenConverter(jwtTokenEnhancer())// 配置JwtAccessToken转换器
//            .userDetailsService(userServiceDetail) // refresh_token需要userDetailsService
//            .authenticationManager(authenticationManager);// WebSecurity配置好的
//    }
//
//    public TokenStore tokenStore() {
//
//        return new JwtTokenStore(jwtTokenEnhancer());
//    }
//
//    private JwtAccessTokenConverter jwtTokenEnhancer() {
//
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("cnsesan-jwt.jks"),
//            "cnsesan123".toCharArray());
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("cnsesan-jwt"));
//        return converter;
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//
//        oauthServer
//            // 开启/oauth/token_key验证端口无权限访问
//            .tokenKeyAccess("permitAll()")
//            // 开启/oauth/check_token验证端口认证权限访问
//            .checkTokenAccess("isAuthenticated()");
//    }
//}
