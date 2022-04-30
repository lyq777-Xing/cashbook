package com.cashbook.controller;


import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import com.cashbook.service.ICkPermiisionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-04-30
 */
@Slf4j
@Controller
@Api(value = "", tags = "", description="")
public class CkPermiisionController {

    @Reference
    private ICkPermiisionService ckPermiisionService;
}
