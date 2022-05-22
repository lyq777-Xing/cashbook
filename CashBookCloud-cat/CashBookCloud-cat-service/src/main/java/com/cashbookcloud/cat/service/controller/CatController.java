package com.cashbookcloud.cat.service.controller;

import com.cashbookcloud.cat.api.dto.CatDto;
import com.cashbookcloud.cat.api.service.CatService;
import com.cashbookcloud.common.result.ResponseResult;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin
@RequestMapping("/cat")
@RestController
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping("/findById")
    public ResponseResult findById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            CatDto catById = catService.findCatById(1);
            result.Success("查询成功",catById);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY("查询失败");
        }
        return result;
    }
}
