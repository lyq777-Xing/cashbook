package com.cashbookcloud.bill.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.bill.api.dto.BillDto;
import com.cashbookcloud.bill.api.service.BillService;
import com.cashbookcloud.bill.service.covert.BillCovert;
import com.cashbookcloud.bill.service.vo.BillVo;
import com.cashbookcloud.bill.service.vo.PageVo;
import com.cashbookcloud.common.result.ResponseResult;
import io.swagger.models.auth.In;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RequestMapping("/bill")
@RestController
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/getAllBill")
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

    @GetMapping("/getall")
    public ResponseResult getAll(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            result.Success("查询成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY("查询失败");
        }
        return result;
    }

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

    @GetMapping("/getById")
    public ResponseResult getById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BillDto billDto = billService.selectById(id);
            BillVo billVo = BillCovert.INSTANCE.dto2vo(billDto);
            result.Success("查询成功",billVo);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY("查询失败");
        }
        return result;
    }

    @PutMapping("/update")
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
}
