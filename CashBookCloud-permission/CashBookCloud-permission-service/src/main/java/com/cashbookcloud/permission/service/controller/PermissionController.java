package com.cashbookcloud.permission.service.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.permission.api.dto.PermissionDto;
import com.cashbookcloud.permission.api.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("权限管理")
//@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 获取权限列表
     * @return
     */
    @ApiOperation(value = "获取权限列表（List）",notes = "获取权限列表",httpMethod = "GET",response = ResponseResult.class)
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

    /**
     * 获取权限列表
     * @return
     */
    @ApiOperation(value = "获取权限列表（Tree）",notes = "获取权限列表",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/gettree")
    public ResponseResult getTree(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<PermissionDto> all = permissionService.findAll();
            result.Success("查询成功",all);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取管理员权限列表
     * @return
     */
    @ApiOperation(value = "获取管理员权限列表",notes = "获取权限列表",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/getadmintree")
    public ResponseResult getAdminTree(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<PermissionDto> allAminList = permissionService.findAllAminList();
            result.Success("查询成功",allAminList);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取用户权限列表
     * @return
     */
    @ApiOperation(value = "获取用户权限列表",notes = "获取权限列表",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/getusertree")
    public ResponseResult getUserTree(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<PermissionDto> allAminList = permissionService.findAllUserList();
            result.Success("查询成功",allAminList);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 根据id查询权限
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询权限",notes = "根据id查询权限",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @GetMapping("/findById")
    public ResponseResult findById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            PermissionDto byId = permissionService.findById(id);
            result.Success("ok!",byId);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 根据Pid查询权限
     * @param pid
     * @return
     */
    @ApiOperation(value = "根据Pid查询权限",notes = "根据Pid查询权限",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "pid")
    @GetMapping("/findByPid")
    public List<PermissionDto> findByPid(Integer pid){
        List<PermissionDto> byPid = permissionService.findByPid(pid);
        return byPid;
    }
}
