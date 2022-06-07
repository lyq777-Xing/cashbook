package com.cashbookcloud.billlist.service.controller;

import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.api.service.BilllistService;
import com.cashbookcloud.billlist.service.covert.BilllistCovert;
import com.cashbookcloud.billlist.service.vo.BilllistVo;
import com.cashbookcloud.billlist.service.vo.RangeVo;
import com.cashbookcloud.common.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/billlist")
public class BilllistController {

    @Autowired
    private BilllistService billlistService;

    @PreAuthorize("hasAuthority('getallbilllist')")
    @GetMapping("/getall")
    public ResponseResult getAllList(Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<BilllistDto> allList = billlistService.getAllList(userId);
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

    @PreAuthorize("hasAuthority('addbilllist')")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody BilllistVo billlistVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
//            判断当前billist的name是否已经被使用
            BilllistDto byName = billlistService.findByName(billlistVo.getBilllistName(), billlistVo.getUserId());
            if(byName != null){
                result.FAIL_NAMEALREDYUSE();
            }else {
                BilllistDto billlistDto = BilllistCovert.INSTANCE.vo2dto(billlistVo);
                BilllistDto add = billlistService.add(billlistDto);
                result.Success("添加成功",add);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    @PreAuthorize("hasAuthority('delbillist')")
    @DeleteMapping("/del")
    public ResponseResult del(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            billlistService.del(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    @PreAuthorize("hasAuthority('updbilllist')")
    @PostMapping("/upd")
    public ResponseResult upd(@RequestBody BilllistVo billlistVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BilllistDto byId = billlistService.findById(billlistVo.getId());
            if (byId.getBilllistName().equals(billlistVo.getBilllistName())){
                BilllistDto billlistDto = BilllistCovert.INSTANCE.vo2dto(billlistVo);
                BilllistDto upd = billlistService.upd(billlistDto);
                result.Success("更新成功",upd);
            }else {
                BilllistDto byName = billlistService.findByName(billlistVo.getBilllistName(),billlistVo.getUserId());
                if(byName != null){
                    result.FAIL_NAMEALREDYUSE();
                }else {
                    BilllistDto billlistDto = BilllistCovert.INSTANCE.vo2dto(billlistVo);
                    BilllistDto upd = billlistService.upd(billlistDto);
                    result.Success("更新成功",upd);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_UPDATE();
        }
        return result;
    }

    @PreAuthorize("hasAuthority('getallbilllist')")
    @GetMapping("/getallb")
    public ResponseResult getAllListb(Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<BilllistDto> allList = billlistService.getAllList(userId);
            result.Success("查询成功",allList);
        }catch (Exception e){
            result.FAIL_QUERY();
        }
        return result;
    }

    @GetMapping("/getdetail")
    public ResponseResult getDetail(){
        ResponseResult<Object> result = new ResponseResult<>();
        String principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        result.Success("ok!",principal);
        return result;
    }

    @GetMapping("/findById")
    public ResponseResult findById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BilllistDto byId = billlistService.findById(id);
            result.Success("查询成功",byId);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }
}
