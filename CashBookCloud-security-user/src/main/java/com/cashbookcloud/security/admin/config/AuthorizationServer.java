package com.cashbookcloud.security.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 设置授权码模式的授权码如何存储，暂时采用内存存储
     * @return
     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(){
//        return new InMemoryAuthorizationCodeServices();
//    }

    /**
     * 使用jdbc存储
     * @param dataSource
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);//设置授权码模式的授权码如何存取
    }

    /**
     * 将客户端信息存储到数据库
     * @param dataSource
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 配置客户端管理
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token");
                .withClientDetails(clientDetailsService)

//        暂时使用内存方式
//        使用in-memory存储
//        clients.inMemory()
////                client-id
//                .withClient("cl")
////                客户端秘钥
//                .secret(new BCryptPasswordEncoder().encode("secret"))
////                资源列表
//                .resourceIds("res1")
////                登录方式
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
////                允许的授权范围
//                .scopes("all")
////                false跳转到授权页面 true直接发令牌
//                .autoApprove(false)
//                .redirectUris("http://www.baidu.com")
//                .and()
////                client-id
//                .withClient("cl")
////                客户端秘钥
//                .secret(new BCryptPasswordEncoder().encode("secret"))
////                资源列表
//                .resourceIds("res1")
////                登录方式
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
////                允许的授权范围
//                .scopes("all")
////                false跳转到授权页面 true直接发令牌
//                .autoApprove(false)
//                .redirectUris("http://www.baidu.com")
        ;
    }



//    令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
//        客户端信息服务
        services.setClientDetailsService(clientDetailsService);
//        是否刷新令牌
        services.setSupportRefreshToken(true);
        services.setTokenStore(tokenStore);
//        令牌增强服务
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);
//        令牌默认有效期限2小时
        services.setAccessTokenValiditySeconds(7200);
//        刷新令牌默认有效期3天
        services.setRefreshTokenValiditySeconds(259200);
        return services;
    }

//    令牌访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        密码模式需要
        endpoints.authenticationManager(authenticationManager)
//                授权码模式需要
                .authorizationCodeServices(authorizationCodeServices)
//                令牌管理服务
                .tokenServices(tokenServices())
//                允许post方法提交
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

//    令牌访问端点的安全策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
//                非对称加密使用
                .tokenKeyAccess("permitAll()")
//                检测令牌 /oauth/check_token公开
                .checkTokenAccess("permitAll()")
//                允许表单认证，申请令牌
                .allowFormAuthenticationForClients();
    }
}
