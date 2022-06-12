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
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
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
    @ApiOperation(value = "获取所有分类（List）",notes = "获取所有分类(List)",httpMethod = "Get",response = ResponseResult.class)
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

    /**
     * 添加分类
     * @param catDto
     * @return
     */
    @ApiOperation(value = "添加分类",notes = "添加分类",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = CatDto.class,required = true,value = "catDto")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody CatDto catDto){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            CatDto byCatName = catService.findByCatName(catDto.getCatName());
            if(byCatName != null ){
                result.FAIL_NAMEALREDYUSE();
            }else {
                catService.add(catDto);
                result.Success("添加成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    /**
     * 修改分类
     * @param catDto
     * @return
     */
    @ApiOperation(value = "修改分类",notes = "修改分类",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = CatDto.class,required = true,value = "catDto")
    @PostMapping("/upd")
    public ResponseResult upd(@RequestBody CatDto catDto){
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            CatDto catById = catService.findCatById(catDto.getId());
            if(catById.getCatName().equals(catDto.getCatName())){
                catService.upd(catDto);
                result.Success("更新成功");
            }else {
                CatDto byCatName = catService.findByCatName(catDto.getCatName());
                if (byCatName != null){
                    result.FAIL_NAMEALREDYUSE();
                }else {
                    catService.upd(catDto);
                    result.Success("更新成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_UPDATE();
        }
        return result;
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @ApiOperation(value = "删除分类",notes = "删除分类",httpMethod = "Delete",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @DeleteMapping("/del")
    public ResponseResult del(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            catService.del(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }
}
