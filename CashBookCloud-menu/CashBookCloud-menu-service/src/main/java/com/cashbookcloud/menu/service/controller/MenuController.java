package com.cashbookcloud.menu.service.controller;

import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.menu.api.dto.MenuDto;
import com.cashbookcloud.menu.api.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin
@RestController
//@RequestMapping("/menus")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
@Api(value = "管理员端左侧菜单栏")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "左侧菜单栏(树形结构)",notes = "左侧菜单栏(树形结构)",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/menus")
    public ResponseResult findAll(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<MenuDto> menusTree = menuService.findMenusTree();
            result.Success("查询成功",menusTree);
        }catch (Exception e){
            e.printStackTrace();
            result.BadRequest("获取菜单栏失败");
        }
        return result;
    }
}
