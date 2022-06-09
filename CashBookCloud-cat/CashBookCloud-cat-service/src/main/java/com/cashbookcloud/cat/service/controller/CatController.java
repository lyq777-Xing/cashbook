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

import java.util.List;

//@CrossOrigin
//@RequestMapping("/cat")
@RestController
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping("/findById")
    public ResponseResult findById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            CatDto catById = catService.findCatById(id);
            result.Success("查询成功",catById);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY("查询失败");
        }
        return result;
    }

    @GetMapping("/getall")
    public ResponseResult getAllCats(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<CatDto> allCat = catService.getAllCat();
            result.Success("查询成功",allCat);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    @GetMapping("/getput")
    public ResponseResult getAllCatPut(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<CatDto> allCatPut = catService.getAllCatPut();
            result.Success("查询成功",allCatPut);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    @GetMapping("/getinput")
    public ResponseResult getInput(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<CatDto> allCatInput = catService.getAllCatInput();
            result.Success("查询成功",allCatInput);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }
}
