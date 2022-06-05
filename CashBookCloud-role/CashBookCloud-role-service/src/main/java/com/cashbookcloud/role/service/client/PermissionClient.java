package com.cashbookcloud.role.service.client;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.role.service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "cashbook-permission-service",configuration={FeignConfig.class})
public interface PermissionClient {

    @GetMapping("/permission/findById")
    public ResponseResult findById(@RequestParam("id") Integer id);
}
