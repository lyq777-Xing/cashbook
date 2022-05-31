package com.cashbookcloud.discovery;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
        (scanBasePackages ={"com.cashbookcloud"})
//@SpringCloudApplication
//@EnableDubbo
public class CashBookDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashBookDiscoveryServiceApplication.class, args);
    }

}
