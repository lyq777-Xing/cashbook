package com.cashbookcloud.permission.service.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.permission.api.dto.PermissionDto;
import com.cashbookcloud.permission.api.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/getall")
    public ResponseResult getAll(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<PermissionDto> all = permissionService.findAllList();
            result.Success("查询成功",all);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }
}