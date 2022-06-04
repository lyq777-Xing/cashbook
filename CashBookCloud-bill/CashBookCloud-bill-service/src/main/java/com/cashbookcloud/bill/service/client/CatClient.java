package com.cashbookcloud.bill.service.client;

import com.cashbookcloud.bill.service.config.FeignConfig;
import com.cashbookcloud.common.result.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value="cashbook-cat-service",configuration={FeignConfig.class})
public interface CatClient {
    @GetMapping("/cat/findById")
    public ResponseResult findById(@RequestParam("id") Integer id);
}
