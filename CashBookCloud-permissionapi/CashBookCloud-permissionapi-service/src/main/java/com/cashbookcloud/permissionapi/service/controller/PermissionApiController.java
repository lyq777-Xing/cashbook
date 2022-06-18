package com.cashbookcloud.permissionapi.service.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.permissionapi.api.dto.PermissionApiDto;
import com.cashbookcloud.permissionapi.api.service.PermissionApiSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("权限api管理")
//@RequestMapping("/permissionapi")
public class PermissionApiController {
    @Autowired
    private PermissionApiSerivce permissionApiSerivce;

    /**
     * 根据id查询权限api
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询权限api",notes = "根据id查询权限api",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @GetMapping("/findbyid")
    public PermissionApiDto findByPermissionId(Integer id){
        PermissionApiDto byPermissionId = permissionApiSerivce.findByPermissionId(id);
        return byPermissionId;
    }
}
