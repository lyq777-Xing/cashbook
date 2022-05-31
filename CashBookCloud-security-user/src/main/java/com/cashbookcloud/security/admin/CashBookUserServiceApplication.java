package com.cashbookcloud.security.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
        (scanBasePackages ={"com.cashbookcloud"})
//@SpringCloudApplication
//@EnableDubbo
public class CashBookUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashBookUserServiceApplication.class, args);
    }

}
