package com.cashbookcloud.cat.service.controller;

import com.cashbookcloud.cat.api.dto.CatDto;
import com.cashbookcloud.cat.api.service.CatService;
import com.cashbookcloud.common.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "分类管理")
public class CatController {

    @Autowired
    private CatService catService;

    /**
     * 根据分类id获取账本信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据分类id获取账本信息",notes = "根据账本id获取账本信息",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
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

    /**
     * 获取所有分类
     * @return
     */
    @ApiOperation(value = "获取所有分类",notes = "获取所有分类",httpMethod = "Get",response = ResponseResult.class)
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

    /**
     * 获取所有支出分类
     * @return
     */
    @ApiOperation(value = "获取所有支出分类",notes = "获取所有支出分类",httpMethod = "Get",response = ResponseResult.class)
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

    /**
     * 获取所有收入分类
     * @return
     */
    @ApiOperation(value = "获取所有收入分类",notes = "获取所有收入分类",httpMethod = "Get",response = ResponseResult.class)
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
