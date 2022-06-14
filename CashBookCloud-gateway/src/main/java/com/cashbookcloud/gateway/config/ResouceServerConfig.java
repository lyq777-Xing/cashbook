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
                    .antMatchers("/menus/v2/api-docs").permitAll()
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
                    .antMatchers("/permission/v2/api-docs").permitAll()
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
                    .antMatchers("/user/v2/api-docs").permitAll()
                    .antMatchers("/user/smscodefour").permitAll()
                    .antMatchers("/user/zhuce").permitAll()
                    .antMatchers("/user/**").access("#oauth2.hasScope('ROLE_USER')");
        }
    }

    /**
     * billlist资源配置
     */
    @Configuration
    @EnableResourceServer
    public class BillListServerConfig extends ResourceServerConfigurerAdapter{
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
                    .antMatchers("/billlist/v2/api-docs").permitAll()
                    .antMatchers("/billlist/zhuceadd/**").permitAll()
                    .antMatchers("/billlist/**").access("#oauth2.hasScope('ROLE_USER')");
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
                    .antMatchers("/role/v2/api-docs").permitAll()
                    .antMatchers("/role/**").access("#oauth2.hasScope('ROLE_USER')");
        }
    }

    /**
     * bill资源配置
     */
    @Configuration
    @EnableResourceServer
    public class BillServerConfig extends ResourceServerConfigurerAdapter{
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
                    .antMatchers("/bill/v2/api-docs").permitAll()
                    .antMatchers("/bill/**").access("#oauth2.hasScope('ROLE_USER')");
        }
    }

    /**
     * cat资源配置
     */
    @Configuration
    @EnableResourceServer
    public class CatServerConfig extends ResourceServerConfigurerAdapter{
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
                    .antMatchers("/cat/v2/api-docs").permitAll()
                    .antMatchers("/cat/**").access("#oauth2.hasScope('ROLE_USER')");
        }
    }

    /**
     * alipay资源配置
     */
    @Configuration
    @EnableResourceServer
    public class AlipayServerConfig extends ResourceServerConfigurerAdapter{
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
                    .antMatchers("/alipay/v2/api-docs").permitAll()
                    .antMatchers("/alipay/**").access("#oauth2.hasScope('ROLE_USER')");
        }
    }

    /**
     * sendmsg资源配置
     */
    @Configuration
    @EnableResourceServer
    public class sendMsgServerConfig extends ResourceServerConfigurerAdapter{
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
                    .antMatchers("/sendmsg/**").permitAll();
//                    .antMatchers("/sendmsg/**").access("#oauth2.hasScope('ROLE_USER')");
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
