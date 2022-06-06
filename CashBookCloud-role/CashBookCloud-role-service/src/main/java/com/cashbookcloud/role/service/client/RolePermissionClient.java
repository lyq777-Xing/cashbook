package com.cashbookcloud.role.service.client;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.role.api.dto.RolePermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient("cashbook-rolepermission-service")
public interface RolePermissionClient {

    @GetMapping("/rolepermission/findByRid")
    public List<RolePermissionDto> findByRid(@RequestParam("rid") Integer rid);

    @DeleteMapping("/rolepermission/del")
    public ResponseResult del(@RequestParam("roleId") Integer roleId,@RequestParam("PermissionId") Integer PermissionId);

    @DeleteMapping("/rolepermission/delbyrid")
    public ResponseResult del(@RequestParam("roleId") Integer roleId);

    @PostMapping("/rolepermission/add/{roleId}/{permissionIds}")
    public ResponseResult add(@PathVariable("roleId") Integer roleId,@PathVariable("permissionIds") Integer[] permissionIds);
}

//@FeignClient注解用于指定从哪个服务中调用功能 ，名称与被调用的服务名保持一致。
