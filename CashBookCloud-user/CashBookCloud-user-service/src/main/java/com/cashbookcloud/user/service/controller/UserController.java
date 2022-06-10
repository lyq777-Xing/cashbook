package com.cashbookcloud.user.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.user.api.dto.ReportDto;
import com.cashbookcloud.user.api.dto.UserDto;
import com.cashbookcloud.user.api.service.UserService;
import com.cashbookcloud.user.service.covert.UserCovert;
import com.cashbookcloud.user.service.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@Api("用户管理")
//@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取所有用户信息
     * @param pagenum
     * @param pagesize
     * @param query
     * @return
     */
    @ApiOperation(value = "获取所有用户信息",notes = "获取所有用户信息",httpMethod = "Get",response = ResponseResult.class)
    @PreAuthorize("hasAuthority('getalluser')")
    @GetMapping("/getall")
    public ResponseResult getAllPage(@RequestParam(required = true) Integer pagenum,
                                     @RequestParam(required = true) Integer pagesize,
                                     @RequestParam(required = false) String query){
        ResponseResult<Object> result = new ResponseResult<>();
        if (query==null){
            query="";
        }
        try{
            Page<UserDto> allPage = userService.findAllPage(pagenum, pagesize, query);
            result.Success("查询成功",allPage);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询用户信息",notes = "根据id查询用户信息",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @PreAuthorize("hasAuthority('getalluser')")
    @GetMapping("/getById")
    public ResponseResult findById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            UserDto byId = userService.findById(id);
            result.Success("查询成功",byId);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @ApiOperation(value = "添加用户",notes = "添加用户",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = UserVo.class,required = true,value = "userVo")
    @PreAuthorize("hasAuthority('adduser')")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody UserVo userVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String userName = userVo.getUserName();
            UserDto byUserName = userService.findByUserName(userName);
            if(byUserName != null){
                result.FAIL_NAMEALREDYUSE();
            }else {
                UserDto userDto = UserCovert.INSTANCE.vo2dto(userVo);
                LocalDate now = LocalDate.now();
                userDto.setUserCreatedate(now);
                String encode = passwordEncoder.encode(userDto.getUserPassword());
                userDto.setUserPassword(encode);
                UserDto add = userService.add(userDto);
                result.Success("添加成功",add);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除用户",notes = "根据id删除用户",httpMethod = "Delete",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @PreAuthorize("hasAuthority('deluser')")
    @DeleteMapping("/del")
    public ResponseResult del(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            userService.del(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    /**
     * 更新用户信息
     * @param userVo
     * @return
     */
    @ApiOperation(value = "更新用户信息",notes = "更新用户信息",httpMethod = "Put",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = UserVo.class,required = true,value = "userVo")
    @PreAuthorize("hasAuthority('upduser')")
    @PutMapping("/upd")
    public ResponseResult upd(@RequestBody UserVo userVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Integer id = userVo.getId();
            UserDto byId = userService.findById(id);
            if(byId.getUserName().equals(userVo.getUserName())){
                UserDto userDto = UserCovert.INSTANCE.vo2dto(userVo);
                UserDto upd = userService.upd(userDto);
                result.Success("更新成功",upd);
            }else {
                UserDto byUserName = userService.findByUserName(userVo.getUserName());
                if(byUserName!=null){
                    result.FAIL_NAMEALREDYUSE();
                }else {
                    UserDto userDto = UserCovert.INSTANCE.vo2dto(userVo);
                    UserDto upd = userService.upd(userDto);
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
     * 根据角色id删除用户
     * @param rid
     * @return
     */
    @ApiOperation(value = "根据角色id删除用户",notes = "根据角色id删除用户",httpMethod = "Delete",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "rid")
    @PreAuthorize("hasAuthority('delrole')")
    @DeleteMapping("/del/user")
    public ResponseResult delByRid(Integer rid){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            userService.delByRid(rid);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    /**
     * 获取角色统计数据
     * @return
     */
    @ApiOperation(value = "获取角色统计数据",notes = "获取角色统计数据",httpMethod = "Get",response = ResponseResult.class)
    @GetMapping("/getreport")
    public ResponseResult getReport(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<ReportDto> report = userService.getReport();
            result.Success("查询成功",report);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }


}
