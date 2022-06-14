package com.cashbookcloud.billlist.service.controller;

import com.cashbookcloud.billlist.api.dto.BilllistDto;
import com.cashbookcloud.billlist.api.service.BilllistService;
import com.cashbookcloud.billlist.service.covert.BilllistCovert;
import com.cashbookcloud.billlist.service.vo.BilllistVo;
import com.cashbookcloud.billlist.service.vo.RangeVo;
import com.cashbookcloud.common.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin
@RestController
//@RequestMapping("/billlist")
@Api(value = "账本管理")
public class BilllistController {

    @Autowired
    private BilllistService billlistService;

    /**
     * 获取所有账本
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据useId获取所有账本,返回range格式",notes = "list形式",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userId")
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

    /**
     * 添加账本
     * @param billlistVo
     * @return
     */
    @ApiOperation(value = "添加账本",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = BilllistVo.class,required = true,value = "BilllistVo")
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

    /**
     * 删除账本
     * @param id
     * @return
     */
    @ApiOperation(value = "删除账本",httpMethod = "Delete",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
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

    /**
     * 更新账本
     * @param billlistVo
     * @return
     */
    @ApiOperation(value = "更新账本",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = BilllistVo.class,required = true,value = "BilllistVo")
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

    /**
     * 根据userId获取其对应的所有账本
     * @param userId
     * @return
     */
    @ApiOperation(value = "根据userId获取其对应的所有账本",notes = "list形式",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userId")
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

    /**
     * 获取登录用户的信息
     * @return
     */
    @ApiOperation(value = "获取登录用户的信息",notes = "获取登录用户的信息",httpMethod = "Get",response = ResponseResult.class)
    @GetMapping("/getdetail")
    public ResponseResult getDetail(){
        ResponseResult<Object> result = new ResponseResult<>();
        String principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        result.Success("ok!",principal);
        return result;
    }

    /**
     * 根据账本id获取账本信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据账本id获取账本信息",notes = "根据账本id获取账本信息",httpMethod = "Get",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
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

    /**
     * 注册用户成功后自动创建两个账本
     * @param userId
     * @return
     */
    @ApiOperation(value = "注册用户成功后自动创建两个账本",httpMethod = "Post",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = BilllistVo.class,required = true,value = "BilllistVo")
//    @PreAuthorize("hasAuthority('addbilllist')")
    @PostMapping("/zhuceadd/{userId}")
    public ResponseResult zhuceadd(@PathVariable("userId") Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BilllistDto billlistDto = new BilllistDto();
            billlistDto.setBilllistImg("efcc1544-cb75-416e-9477-ffc4900fd017.jpg");
            billlistDto.setBilllistName("日常支出");
            billlistDto.setUserId(userId);
            BilllistDto add = billlistService.add(billlistDto);
            BilllistDto billlistDto2 = new BilllistDto();
            billlistDto2.setBilllistImg("4e9ec262-453f-4976-b40e-666028de0864.jpg");
            billlistDto2.setBilllistName("生活开销");
            billlistDto2.setUserId(userId);
            BilllistDto add2 = billlistService.add(billlistDto2);
            result.Success("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }
}
