package com.cashbookcloud.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .and().csrf().disable();
//    }
//}

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig  {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
//                .matchers(EndpointRequest.to(HealthEndpoint.class, InfoEndpoint.class)).permitAll()
                .pathMatchers("/**").permitAll()
//                .pathMatchers(adminServer.path("/login")).permitAll()
//                .anyExchange().authenticated()
        );
//        http.pa
//                .authorizeRequests()
//                .antMatchers("/**").permitAll();
        //配置白名单和访问规则，CommonEnum枚举类
        http.csrf().disable();
        return http.build();
    }
}
