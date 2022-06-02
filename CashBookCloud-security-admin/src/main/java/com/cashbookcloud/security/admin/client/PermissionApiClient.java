package com.cashbookcloud.security.admin.client;

import com.cashbookcloud.security.admin.getAuthorization.entity.PermissionApiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("cashbook-permissionapi-service")
public interface PermissionApiClient {

    @GetMapping("/permissionapi/findbyid")
    public PermissionApiDto findByPermissionId(@RequestParam("id") Integer id);
}
