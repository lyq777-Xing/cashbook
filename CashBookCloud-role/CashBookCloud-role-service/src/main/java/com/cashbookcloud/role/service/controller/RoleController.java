package com.cashbookcloud.role.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.role.api.dto.RoleDto;
import com.cashbookcloud.role.api.service.RoleService;
import com.cashbookcloud.role.service.covert.RoleCovert;
import com.cashbookcloud.role.service.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

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
}
