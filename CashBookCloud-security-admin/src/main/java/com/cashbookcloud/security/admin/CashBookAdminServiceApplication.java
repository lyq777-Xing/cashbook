package com.cashbookcloud.security.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages ={"com.cashbookcloud"})
@EnableFeignClients
//@SpringCloudApplication
//@EnableDubbo
public class CashBookAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashBookAdminServiceApplication.class, args);
    }

}
