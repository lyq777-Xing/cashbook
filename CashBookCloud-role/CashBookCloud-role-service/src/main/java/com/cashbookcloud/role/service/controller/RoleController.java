package com.cashbookcloud.role.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.role.api.dto.PermissionDto;
import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.api.service.RoleService;
import com.cashbookcloud.role.service.covert.RoleCovert;
import com.cashbookcloud.role.service.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/role")
@Api("角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色列表（Page）
     * @param pagenum
     * @param pagesize
     * @param query
     * @return
     */
    @ApiOperation(value = "获取所有角色列表（Page）",notes = "获取所有角色列表（Page）",httpMethod = "Get",response = ResponseResult.class)
    @PreAuthorize("hasAuthority('getallrole')")
    @GetMapping("/getall")
    public ResponseResult getAllPage(@RequestParam(required = true) Integer pagenum,
                                     @RequestParam(required = true) Integer pagesize,
                                     @RequestParam(required = false) String query){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Page<RoleDto> allPage = roleService.findAllPage(pagenum, pagesize, query);
            result.Success("查询成功",allPage);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 添加角色
     * @param roleVo
     * @return
     */
    @ApiOperation(value = "添加角色",notes = "添加角色",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = RoleVo.class,required = true,value = "RoleVo")
    @PreAuthorize("hasAuthority('addrole')")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody RoleVo roleVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            RoleDto roleDto = RoleCovert.INSTANCE.vo2dto(roleVo);
            String roleName = roleDto.getRoleName();
            RoleDto byName = roleService.findByName(roleName);
            if(byName != null){
                result.FAIL_NAMEALREDYUSE();
            }else {
                RoleDto add = roleService.add(roleDto);
                result.Success("添加成功",add);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除角色",notes = "根据id删除角色",httpMethod = "Delete",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @PreAuthorize("hasAuthority('delrole')")
    @DeleteMapping("/del")
    public ResponseResult del(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            roleService.del(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }


    /**
     * 更新角色信息
     * @param roleVo
     * @return
     */
    @ApiOperation(value = "更新角色信息",notes = "更新角色信息",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = RoleVo.class,required = true,value = "RoleVo")
    @PreAuthorize("hasAuthority('updrole')")
    @PostMapping("/upd")
    public ResponseResult upd(@RequestBody RoleVo roleVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            RoleDto roleDto = RoleCovert.INSTANCE.vo2dto(roleVo);
            String roleName = roleDto.getRoleName();
            RoleDto byId = roleService.findById(roleDto.getId());
            if(byId.getRoleName().equals(roleName)){
                RoleDto upd = roleService.upd(roleDto);
                result.Success("更新成功",upd);
            }else {
                RoleDto byName = roleService.findByName(roleName);
                if(byName != null){
                    result.FAIL_NAMEALREDYUSE();
                }else {
                    RoleDto upd = roleService.upd(roleDto);
                    result.Success("更新成功",upd);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_UPDATE();
        }
        return result;
    }

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询角色",notes = "根据id查询角色",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @PreAuthorize("hasAnyAuthority('getallrole','getallmanager')")
    @GetMapping("/getById")
    public ResponseResult getById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            RoleDto byId = roleService.findById(id);
            result.Success("查询成功",byId);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取所有管理员角色
     * @return
     */
    @ApiOperation(value = "获取所有管理员角色",notes = "获取所有管理员角色",httpMethod = "Get",response = ResponseResult.class)
    @PreAuthorize("hasAnyAuthority('updmanager','upduser','getallrole')")
    @GetMapping("/getalladmin")
    public ResponseResult getAllAdmin(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<RoleDto> allAdmin = roleService.findAllAdmin();
            result.Success("查询成功",allAdmin);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取所有用户角色
     * @return
     */
    @ApiOperation(value = "获取所有用户角色",notes = "获取所有用户角色",httpMethod = "Get",response = ResponseResult.class)
    @PreAuthorize("hasAnyAuthority('updmanager','upduser','getallrole')")
    @GetMapping("/getalluser")
    public ResponseResult getAllUser(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<RoleDto> allUser = roleService.findAllUser();
            result.Success("查询成功",allUser);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 根据id查询对应权限
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询对应权限",notes = "根据id查询对应权限",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @GetMapping("/getpermissionbyroleid")
    public ResponseResult getPermissionByRoleId(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            RoleDto permissionByRoleId = roleService.findPermissionByRoleId(id);
            result.Success("查询成功",permissionByRoleId);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 删除角色的权限
     * @param roleId
     * @param permissionId
     * @return
     */
    @ApiOperation(value = "删除角色的权限",notes = "删除角色的权限",httpMethod = "Delete",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "roleId，permissionId")
    @DeleteMapping("/delrolepermission")
    public ResponseResult delRolePermission(Integer roleId,Integer permissionId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<PermissionDto> permissionDtos = roleService.removeRightByPermissionId(roleId, permissionId);
            result.Success("删除成功",permissionDtos);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }


    /**
     * 更新角色的权限
     * @param roleId
     * @param permissionIds
     * @return
     */
    @ApiOperation(value = "更新角色的权限",notes = "更新角色的权限",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "roleId，permissionIds")
    @PostMapping("/updrolerights/{roleId}/{permissionIds}")
    public ResponseResult updRoleRights(@PathVariable("roleId") Integer roleId,@PathVariable("permissionIds") Integer[] permissionIds){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            roleService.updRolePermissionByRoleIAndPermissionIds(roleId,permissionIds);
            result.Success("更新权限成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_UPDATE();
        }
        return result;
    }

}
