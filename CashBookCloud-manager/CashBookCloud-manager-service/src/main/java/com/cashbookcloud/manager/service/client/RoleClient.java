package com.cashbookcloud.manager.service.client;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.manager.service.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient（value="被调用的服务名",configuration={FeignConfig.class}）

@Component
@FeignClient(value="cashbook-role-service",configuration={FeignConfig.class})
public interface RoleClient {
    @GetMapping("/role/getById")
    public ResponseResult getById(@RequestParam("id") Integer id);
}
