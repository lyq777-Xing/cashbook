package com.cashbookcloud.billlist.service.controller;

import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.api.service.BilllistService;
import com.cashbookcloud.billlist.service.vo.RangeVo;
import com.cashbookcloud.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/billlist")
public class BilllistController {

    @Autowired
    private BilllistService billlistService;

    @GetMapping("/getall")
    public ResponseResult getAllList(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<BilllistDto> allList = billlistService.getAllList();
            ArrayList<RangeVo> rangeVos = new ArrayList<>();
            for (int i = 0; i < allList.size(); i++) {
                RangeVo rangeVo = new RangeVo();
                rangeVo.setValue(allList.get(i).getId());
                rangeVo.setText(allList.get(i).getBilllistName());
                rangeVos.add(rangeVo);
            }
            result.Success("查询成功",rangeVos);
        }catch (Exception e){
            result.FAIL_QUERY();
        }
        return result;
    }
}
