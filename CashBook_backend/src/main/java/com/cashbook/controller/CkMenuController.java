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
@Api(value = "菜单", tags = "", description="")
public class CkMenuController {

    @Reference
    private ICkMenuService ckMenuService;

    @GetMapping("/menus")
    public Map<String, Object> findAll(){
        Map<String, Object> map = ckMenuService.getMap(null);
        return map;
    }
}
