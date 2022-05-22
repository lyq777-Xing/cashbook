package com.cashbookcloud.manager.service.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.manager.api.dto.ManagerDto;
import com.cashbookcloud.manager.api.service.ManagerService;
import com.cashbookcloud.manager.service.covert.ManagerCovert;
import com.cashbookcloud.manager.service.vo.ManagerVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 获取管理员列表（带分页） 并且根据query查询信息
     */
//    @ApiOperation("获取管理员列表（带分页） 并且根据query查询信息")
//    @ApiImplicitParam(value = "query",required = false)
    @GetMapping("/getall")
    public ResponseResult getAllAdminPage(@RequestParam(required = true) Integer pagenum,
                                          @RequestParam(required = true) Integer pagesize,
                                          @RequestParam(required = false) String query){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Page<ManagerDto> page = managerService.findAllManagerPage(pagenum, pagesize, query);
            if(page == null){
                result.ISNULL();
            }else {
                result.Success("查询成功",page);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    @DeleteMapping("/del")
    public ResponseResult del(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            managerService.deleteById(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    @PostMapping("/add")
    public  ResponseResult add(@RequestBody ManagerVo managerVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            ManagerDto managerDto = ManagerCovert.INSTANCE.vo2dto(managerVo);
            ManagerDto managerDto1 = managerService.addManager(managerDto);
            result.Success("添加成功",managerDto1);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    @PostMapping("/upd")
    public ResponseResult upd(@RequestBody ManagerVo managerVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            ManagerDto managerDto = ManagerCovert.INSTANCE.vo2dto(managerVo);
            ManagerDto managerDto1 = managerService.updateById(managerDto);
            result.Success("更新成功",managerDto1);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

}
