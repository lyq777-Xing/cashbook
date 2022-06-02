package com.cashbookcloud.rolepermission.service.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.rolepermission.api.dto.RolePermissionDto;
import com.cashbookcloud.rolepermission.api.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rolepermission")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/test")
    public ResponseResult test(){
        ResponseResult<Object> result = new ResponseResult<>();
        result.Success();
        return result;
    }

    @GetMapping("/findByRid")
    public List<RolePermissionDto> findById(Integer rid){
        List<RolePermissionDto> byRoleId = rolePermissionService.findByRoleId(rid);
        return byRoleId;
    }
}
