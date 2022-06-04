package com.cashbookcloud.billlist.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
        (scanBasePackages ={"com.cashbookcloud"})
//@SpringCloudApplication
@EnableDubbo
public class CashBookBilllistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashBookBilllistServiceApplication.class, args);
    }

}
