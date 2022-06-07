package com.cashbookcloud.bill.service.client;

import com.cashbookcloud.bill.service.config.FeignConfig;
import com.cashbookcloud.common.result.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value="cashbook-billlist-service",configuration={FeignConfig.class})
public interface BilllistClient {

    @GetMapping("/billlist/findById")
    public ResponseResult findById(@RequestParam("id") Integer id);
}


