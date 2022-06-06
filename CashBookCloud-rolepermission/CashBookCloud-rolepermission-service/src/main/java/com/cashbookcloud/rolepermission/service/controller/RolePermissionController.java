package com.cashbookcloud.rolepermission.service.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.rolepermission.api.dto.RolePermissionDto;
import com.cashbookcloud.rolepermission.api.service.RolePermissionService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/del")
    public ResponseResult del(Integer roleId, Integer PermissionId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            rolePermissionService.delByRoleIdAndPermissionId(roleId, PermissionId);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    @DeleteMapping("/delbyrid")
    public ResponseResult del(Integer roleId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            rolePermissionService.delByRoleId(roleId);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    @PostMapping("/add/{roleId}/{permissionIds}")
    public ResponseResult add(@PathVariable("roleId") Integer roleId,@PathVariable("permissionIds") Integer[] permissionIds){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            rolePermissionService.add(roleId, permissionIds);
            result.Success("添加成功");
        }catch (Exception e){
            result.FAIL_ADD();
        }
        return result;
    }

}
