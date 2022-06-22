package com.cashbookcloud.security.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
        (scanBasePackages ={"com.cashbookcloud"})
//@EnableHystrix
//@SpringCloudApplication
//@EnableDubbo
public class CashBookSecurityUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashBookSecurityUserServiceApplication.class, args);
    }

}
