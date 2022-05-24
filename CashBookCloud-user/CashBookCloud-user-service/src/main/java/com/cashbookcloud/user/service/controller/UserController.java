package com.cashbookcloud.user.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.user.api.dto.UserDto;
import com.cashbookcloud.user.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

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



}
