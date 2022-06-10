package com.cashbookcloud.bill.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.bill.api.dto.BillDto;
import com.cashbookcloud.bill.api.dto.CashReportDto;
import com.cashbookcloud.bill.api.dto.CatReportDto;
import com.cashbookcloud.bill.api.dto.KeepingDto;
import com.cashbookcloud.bill.api.service.BillService;
import com.cashbookcloud.bill.service.covert.BillCovert;
import com.cashbookcloud.bill.service.vo.BillVo;
import com.cashbookcloud.bill.service.vo.CountVo;
import com.cashbookcloud.bill.service.vo.PageVo;
import com.cashbookcloud.common.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@CrossOrigin
//@RequestMapping("/bill")
@RestController
@Api("账单管理服务")
public class BillController {

    @Autowired
    private BillService billService;

    /**
     * 获取所有账单
     * @param pageVo
     * @return
     */
    @ApiOperation(value = "获取所有账单",notes = "分页格式 ",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/getall")
    public ResponseResult getAllPage(@RequestBody PageVo pageVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Page<BillDto> allPage = billService.findAllPage(pageVo.getPagenum(), pageVo.getPagesize(),pageVo.getQuery());
            List<BillVo> billDtos = new ArrayList<>();
            Page<BillVo> billVoPage = new Page<>();
            for (int i = 0; i < allPage.getRecords().size(); i++) {
                billDtos.add(BillCovert.INSTANCE.dto2vo(allPage.getRecords().get(i)));
            }
            billVoPage.setRecords(billDtos);
            billVoPage.setTotal(allPage.getTotal());
            billVoPage.setCountId(allPage.getCountId());
            billVoPage.setCurrent(allPage.getCurrent());
            billVoPage.setMaxLimit(allPage.getMaxLimit());
            billVoPage.setSize(allPage.getSize());
            billVoPage.setPages(allPage.getPages());
            result.Success("查询成功",billVoPage);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY("查询失败");
        }
        return result;
    }

//    @GetMapping("/getalltest")
//    public ResponseResult getAll(){
//        ResponseResult<Object> result = new ResponseResult<>();
//        try{
//            result.Success("查询成功");
//        }catch (Exception e){
//            e.printStackTrace();
//            result.FAIL_QUERY("查询失败");
//        }
//        return result;
//    }

    /**
     * 添加账单
     * @param billVo
     * @return
     */
    @ApiOperation(value = "添加账单",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = BillVo.class,required = true,value = "账单Vo")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody BillVo billVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BillDto billDto = BillCovert.INSTANCE.vo2dto(billVo);
            BillDto billDto1 = billService.addBill(billDto);
            BillVo billVo1 = BillCovert.INSTANCE.dto2vo(billDto1);
            result.Success("添加成功",billVo1);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    /**
     * 删除账单
     * @param id
     * @return
     */
    @ApiOperation(value = "删除账单",notes = "参数id ",httpMethod = "Delete",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @DeleteMapping("/del")
    public ResponseResult delete(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            billService.deleteBillById(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    /**
     * 根据id查询账单
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询账单",notes = "参数id ",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @GetMapping("/getById")
    public ResponseResult getById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BillDto billDto = billService.selectById(id);
//            BillVo billVo = BillCovert.INSTANCE.dto2vo(billDto);
            result.Success("查询成功",billDto);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY("查询失败");
        }
        return result;
    }

    /**
     * 更新账单
     * @param billVo
     * @return
     */
    @ApiOperation(value = "更新账单",httpMethod = "Put",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = BillVo.class,required = true,value = "billVo")
    @PutMapping("/upd")
    public ResponseResult update(@RequestBody BillVo billVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BillDto billDto = BillCovert.INSTANCE.vo2dto(billVo);
            BillDto billDto1 = billService.updateById(billDto);
            result.Success("更新成功",billDto1);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_UPDATE();
        }
        return result;
    }

    /**
     * 获取账单结余支出收入信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取账单结余支出收入信息",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @GetMapping("/getkeeping")
    public ResponseResult getKeeping(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            KeepingDto keeping = billService.getKeeping(id);
            result.Success("查询成功",keeping);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取根据账本id获取对应账单数量
     * @param billlistId
     * @return
     */
    @ApiOperation(value = "获取根据账本id获取对应账单数量",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "billlistId")
    @GetMapping("/getcount")
    public ResponseResult getCount(Integer billlistId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Integer count = billService.getCount(billlistId);
            CountVo countVo = new CountVo();
            countVo.setCount(count);
            result.Success("ok",countVo);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取根据用户id和账本id获取对应账单
     * @param userId
     * @param billlistId
     * @return
     */
    @ApiOperation(value = "获取根据用户id和账本id获取对应账单",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userId,billlistId")
    @GetMapping("/get")
    public ResponseResult Get(Integer userId,Integer billlistId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<BillDto> list = billService.findAllByUserIdAndBilllistId(userId, billlistId);
            result.Success("查询成功",list);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取用户每日支出图表
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户每日支出图表",notes = "与用户选择的账本无关",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userId")
    @GetMapping("/getreportone")
    public ResponseResult getReportOne(Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            CashReportDto reportOne = billService.getReportOne(userId);
            result.Success("查询成功",reportOne);
        }catch (Exception e){
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取用户分类支出图表
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户分类支出图表",notes = "与用户选择的账本无关",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userId")
    @GetMapping("/getreporttwo")
    public ResponseResult getReportTwo(Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            List<CatReportDto> reportTwo = billService.getReportTwo(userId);
            result.Success("查询成功",reportTwo);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }
}
