package com.cashbookcloud.rolepermission.service.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.rolepermission.api.dto.RolePermissionDto;
import com.cashbookcloud.rolepermission.api.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rolepermission")
@Api("角色权限关联表管理")
public class RolePermissionController {
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/test")
    public ResponseResult test(){
        ResponseResult<Object> result = new ResponseResult<>();
        result.Success();
        return result;
    }

    /**
     * 根据角色id查询对应的权限idList
     * @param rid
     * @return
     */
    @ApiOperation(value = "根据角色id查询对应的权限idList",notes = "根据角色id查询对应的权限idList",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "rid")
    @GetMapping("/findByRid")
    public List<RolePermissionDto> findById(Integer rid){
        List<RolePermissionDto> byRoleId = rolePermissionService.findByRoleId(rid);
        return byRoleId;
    }

    /**
     * 根据角色id和权限id删除表数据
     * @param roleId
     * @param PermissionId
     * @return
     */
    @ApiOperation(value = "根据角色id和权限id删除表数据",notes = "根据角色id和权限id删除表数据",httpMethod = "DELETE",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "rid,PermissionId")
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

    /**
     * 根据角色id删除关联表数据
     * @param roleId
     * @return
     */
    @ApiOperation(value = "根据角色id删除关联表数据",notes = "根据角色id删除关联表数据",httpMethod = "DELETE",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "roleId")
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

    /**
     * 根据角色id和权限id列表添加表数据
     * @param roleId
     * @param permissionIds
     * @return
     */
    @ApiOperation(value = "根据角色id和权限id列表添加表数据",notes = "根据角色id和权限id列表添加表数据",httpMethod = "POST",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "roleId,permissionIds")
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
