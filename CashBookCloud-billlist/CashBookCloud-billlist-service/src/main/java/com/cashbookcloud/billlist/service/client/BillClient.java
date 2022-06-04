package com.cashbookcloud.billlist.service.client;

import com.cashbookcloud.billlist.service.config.FeignConfig;
import com.cashbookcloud.common.result.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="cashbook-bill-service",configuration={FeignConfig.class})
@Component
public interface BillClient {

    @GetMapping("/bill/getcount")
    public ResponseResult getCount(@RequestParam("billlistId") Integer billlistId);

    @GetMapping("/bill/getkeeping")
    public ResponseResult getKeeping(@RequestParam("id") Integer id);
}
