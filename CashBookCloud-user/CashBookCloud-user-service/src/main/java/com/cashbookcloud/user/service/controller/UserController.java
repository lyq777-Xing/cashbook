package com.cashbookcloud.user.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.user.api.dto.UserDto;
import com.cashbookcloud.user.api.service.UserService;
import com.cashbookcloud.user.service.covert.UserCovert;
import com.cashbookcloud.user.service.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

}
