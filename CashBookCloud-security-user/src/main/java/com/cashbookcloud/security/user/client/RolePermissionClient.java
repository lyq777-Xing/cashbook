package com.cashbookcloud.security.user.client;

import com.cashbookcloud.security.user.getAuthorization.dto.RolePermissionDto;
//import com.cashbookcloud.security.user.service.HystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "cashbook-rolepermission-service")
public interface RolePermissionClient {
//    @GetMapping("rolepermission/test")
//    public ResponseResult test(@RequestParam String args);

    @GetMapping("/rolepermission/findByRid")
    public List<RolePermissionDto> findByRid(@RequestParam("rid") Integer rid);
}

//@FeignClient注解用于指定从哪个服务中调用功能 ，名称与被调用的服务名保持一致。
