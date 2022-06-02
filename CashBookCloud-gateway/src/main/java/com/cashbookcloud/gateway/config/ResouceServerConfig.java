package com.cashbookcloud.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "res1";

    /**
     * users资源配置
     */
    @Configuration
    @EnableResourceServer
    public class UsersServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/users/**").permitAll();
        }
    }

    /**
     * admin资源配置
     */
    @Configuration
    @EnableResourceServer
    public class AdminServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/admin/**").permitAll();
//                    .antMatchers("/admin/**").access("#oauth2.hasScope('ROLE_ADMIN')");
        }
    }


    /**
     * menu资源配置
     */
    @Configuration
    @EnableResourceServer
    public class MenuServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/menus/**").access("#oauth2.hasScope('ROLE_ADMIN')");
//                    .antMatchers("/admin/**").access("#oauth2.hasScope('ROLE_ADMIN')");
        }
    }

    /**
     * upload资源配置
     */
    @Configuration
    @EnableResourceServer
    public class UploadApiServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/upload/**").permitAll();
//                    .antMatchers("/admin/**").access("#oauth2.hasScope('ROLE_ADMIN')");
        }
    }

    /**
     * permission资源配置
     */
    @Configuration
    @EnableResourceServer
    public class PermissionApiServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/permission/**").access("#oauth2.hasScope('ROLE_ADMIN')");
//                    .antMatchers("/admin/**").access("#oauth2.hasScope('ROLE_ADMIN')");
        }
    }

    /**
     * user资源配置
     */
    @Configuration
    @EnableResourceServer
    public class UserServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/user/**").access("#oauth2.hasScope('ROLE_USER')");
        }
    }

    /**
     * role资源配置
     */
    @Configuration
    @EnableResourceServer
    public class RoleServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources){
            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID)
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/role/**").access("#oauth2.hasScope('ROLE_USER')");
        }
    }

//    配置其他微服务
//      todo

//    @Autowired
//    TokenStore tokenStore;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID)//资源 id
//                //自己验证令牌的服务
//                .tokenStore(tokenStore)
////                .tokenServices(tokenService())//验证令牌的服务
//                .stateless(true);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                .antMatchers("/**").access("#oauth2.hasScope('all')")
//                .and().csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }

/*    //资源服务令牌解析服务
    @Bean
    public ResourceServerTokenServices tokenService() {
        //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
        RemoteTokenServices service=new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://localhost:8011/user/oauth/check_token");
        service.setClientId("cl");
        service.setClientSecret("secret");
        return service;
    }*/

}
