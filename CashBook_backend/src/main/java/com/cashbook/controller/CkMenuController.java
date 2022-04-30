package com.cashbook.controller;


import com.cashbook.entity.CkMenu;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import com.cashbook.service.ICkMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-04-30
 */
@Slf4j
@RestController
@Api(value = "菜单栏", tags = "", description="")
public class CkMenuController {

    @Reference
    private ICkMenuService ckMenuService;

    @ApiOperation(value = "左侧菜单栏(树形结构)")
    @GetMapping("/menus")
    public List<CkMenu> findAll(){
        List<CkMenu> menusTree = ckMenuService.findMenusTree();
        return menusTree;
    }
}
