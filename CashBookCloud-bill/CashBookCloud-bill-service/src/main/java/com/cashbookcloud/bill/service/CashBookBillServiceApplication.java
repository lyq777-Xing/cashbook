package com.cashbookcloud.bill.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
        (scanBasePackages ={"com.cashbookcloud"})
//@SpringCloudApplication
//@EnableDubbo
public class CashBookBillServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashBookBillServiceApplication.class, args);
    }

}
