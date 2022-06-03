package com.cashbookcloud.billlist.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {



    //    对称加密 只需要采用同样的秘钥即可
    private String SIGNING_KEY = "security";

    //    采用jwt的token
    @Bean
    public TokenStore tokenStore(){
//        JWT令牌存储妨碍
        return new JwtTokenStore(accessTokenCoerter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenCoerter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        对称秘钥，资源服务器使用该秘钥来验证
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
////    令牌存储策略
//    @Bean
//    public TokenStore tokenStore(){
////        内存方式，生成普通令牌
//        return new InMemoryTokenStore();
//    }
}
