package com.cashbookcloud.manager.service.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.manager.api.dto.ManagerDto;
import com.cashbookcloud.manager.api.service.ManagerService;
import com.cashbookcloud.manager.service.common.EncryptUtil;
import com.cashbookcloud.manager.service.covert.ManagerCovert;
import com.cashbookcloud.manager.service.vo.ManagerVo;
import com.cashbookcloud.manager.service.vo.ManagerVovv;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Date;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 获取管理员列表（带分页） 并且根据query查询信息
     */
//    @ApiOperation("获取管理员列表（带分页） 并且根据query查询信息")
//    @ApiImplicitParam(value = "query",required = false)
    @PreAuthorize("hasAuthority('getallmanager')")
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

    @PreAuthorize("hasAuthority('delmanager')")
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

    @PreAuthorize("hasAuthority('addmanager')")
    @PostMapping("/add")
    public  ResponseResult add(@RequestBody ManagerDto managerDto){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
//          根据name判断该用户名是否已经被占用
            ManagerDto byUsername = managerService.findByUsername(managerDto.getMgName());
            if(byUsername!= null){
                result.FAIL_NAMEALREDYUSE();
            }else {
                String encode = passwordEncoder.encode(managerDto.getMgPassword());
                managerDto.setMgPassword(encode);
                ManagerDto managerDto1 = managerService.addManager(managerDto);
                result.Success("添加成功",managerDto1);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    @PreAuthorize("hasAuthority('updmanager')")
    @PostMapping("/upd")
    public ResponseResult upd(@RequestBody ManagerVo managerVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            ManagerDto byId = managerService.findById(managerVo.getId());
            if(byId.getMgName().equals(managerVo.getMgName())){
                ManagerDto managerDto = ManagerCovert.INSTANCE.vo2dto(managerVo);
                ManagerDto managerDto1 = managerService.updateById(managerDto);
                result.Success("更新成功",managerDto1);
            }else {
                ManagerDto byUsername = managerService.findByUsername(managerVo.getMgName());
                if(byUsername != null){
                    result.FAIL_NAMEALREDYUSE();
                }else {
                    ManagerDto managerDto = ManagerCovert.INSTANCE.vo2dto(managerVo);
                    ManagerDto managerDto1 = managerService.updateById(managerDto);
                    result.Success("更新成功",managerDto1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    @PreAuthorize("hasAuthority('getallmanager')")
    @GetMapping("/getById")
    public ResponseResult findById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            ManagerDto byId = managerService.findById(id);
            result.Success("查询成功",byId);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    @DeleteMapping("/del/manager")
    public ResponseResult delByRid(Integer rid){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            managerService.delByRoleId(rid);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    @GetMapping("/getImg")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseResult test() throws JsonProcessingException {
        ResponseResult<Object> result = new ResponseResult<>();
        String principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        ManagerVovv json = (ManagerVovv) JSON.toJSON(principal);
//        String string = JSON.toJSONString(principal);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.registerModule(new JavaTimeModule());
//        ManagerVovv ManagerVo = objectMapper.convertValue(principal, ManagerVovv.class);
//        ManagerVovv ManagerVo = objectMapper.readValue(principal, ManagerVovv.class);
        result.Success("ok!",principal);
        return result;
    }

}
