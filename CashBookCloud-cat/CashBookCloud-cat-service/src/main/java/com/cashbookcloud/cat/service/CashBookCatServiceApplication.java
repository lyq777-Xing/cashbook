package com.cashbookcloud.cat.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
        (scanBasePackages ={"com.cashbookcloud"})
//@SpringCloudApplication
@EnableDubbo
public class CashBookCatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashBookCatServiceApplication.class, args);
    }

}
