package com.cashbookcloud.permissionapi.service.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.permissionapi.api.dto.PermissionApiDto;
import com.cashbookcloud.permissionapi.api.service.PermissionApiSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissionapi")
public class PermissionApiController {
    @Autowired
    private PermissionApiSerivce permissionApiSerivce;

    @GetMapping("/findbyid")
    public PermissionApiDto findByPermissionId(Integer id){
        PermissionApiDto byPermissionId = permissionApiSerivce.findByPermissionId(id);
        return byPermissionId;
    }
}
