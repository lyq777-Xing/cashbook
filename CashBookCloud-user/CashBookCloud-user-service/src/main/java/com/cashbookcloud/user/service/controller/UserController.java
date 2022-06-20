package com.cashbookcloud.user.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.common.constant.RedisMessageConstant;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.common.utils.SendSmsUtils;
import com.cashbookcloud.common.utils.ValidateCodeUtils;
import com.cashbookcloud.user.api.dto.ReportDto;
import com.cashbookcloud.user.api.dto.UserDto;
import com.cashbookcloud.user.api.service.UserService;
import com.cashbookcloud.user.service.covert.UserCovert;
import com.cashbookcloud.user.service.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.time.LocalDate;
import java.util.List;

@RestController
@Api("用户管理")
//@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JedisPool jedisPool;


    /**
     * 获取所有用户信息
     * @param pagenum
     * @param pagesize
     * @param query
     * @return
     */
    @ApiOperation(value = "获取所有用户信息",notes = "获取所有用户信息",httpMethod = "GET",response = ResponseResult.class)
//    @ApiImplicitParam(dataTypeClass = String.class,required = true,value = "phone")
    @ApiImplicitParams({
            @ApiImplicitParam(dataTypeClass = Integer.class, required = true, value = "pagenum"),
            @ApiImplicitParam(dataTypeClass = Integer.class, required = true, value = "pagesize"),
            @ApiImplicitParam(dataTypeClass = String.class, required = true, value = "query"),
    })
    @PreAuthorize("hasAuthority('getalluser')")
    @GetMapping("/getall")
    public ResponseResult getAllPage(@RequestParam(required = true) Integer pagenum,
                                     @RequestParam(required = true) Integer pagesize,
                                     @RequestParam(required = false) String query){
        ResponseResult<Object> result = new ResponseResult<>();
        if (query==null){
            query="";
        }
        try{
            Page<UserDto> allPage = userService.findAllPage(pagenum, pagesize, query);
            result.Success("查询成功",allPage);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询用户信息",notes = "根据id查询用户信息",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @PreAuthorize("hasAuthority('getalluser')")
    @GetMapping("/getById")
    public ResponseResult findById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            UserDto byId = userService.findById(id);
            result.Success("查询成功",byId);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @ApiOperation(value = "添加用户",notes = "添加用户",httpMethod = "POST",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = UserVo.class,required = true,value = "userVo")
    @PreAuthorize("hasAuthority('adduser')")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody UserVo userVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String userName = userVo.getUserName();
            UserDto byUserName = userService.findByUserName(userName);
            if(byUserName != null){
                result.FAIL_NAMEALREDYUSE();
            }else {
                UserDto userDto = UserCovert.INSTANCE.vo2dto(userVo);
                LocalDate now = LocalDate.now();
                userDto.setUserCreatedate(now);
                String encode = passwordEncoder.encode(userDto.getUserPassword());
                userDto.setUserPassword(encode);
                UserDto add = userService.add(userDto);
                result.Success("添加成功",add);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除用户",notes = "根据id删除用户",httpMethod = "DELETE",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @PreAuthorize("hasAuthority('deluser')")
    @DeleteMapping("/del")
    public ResponseResult del(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            userService.del(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    /**
     * 更新用户信息
     * @param userVo
     * @return
     */
    @ApiOperation(value = "更新用户信息",notes = "更新用户信息",httpMethod = "PUT",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = UserVo.class,required = true,value = "userVo")
    @PreAuthorize("hasAnyAuthority('upduser','updwine')")
    @PutMapping("/upd")
    public ResponseResult upd(@RequestBody UserVo userVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Integer id = userVo.getId();
            UserDto byId = userService.findById(id);
            if(byId.getUserName().equals(userVo.getUserName())){
                UserDto userDto = UserCovert.INSTANCE.vo2dto(userVo);
                UserDto upd = userService.upd(userDto);
                result.Success("更新成功",upd);
            }else {
                UserDto byUserName = userService.findByUserName(userVo.getUserName());
                if(byUserName!=null){
                    result.FAIL_NAMEALREDYUSE();
                }else {
                    UserDto userDto = UserCovert.INSTANCE.vo2dto(userVo);
                    UserDto upd = userService.upd(userDto);
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
     * 根据角色id删除用户
     * @param rid
     * @return
     */
    @ApiOperation(value = "根据角色id删除用户",notes = "根据角色id删除用户",httpMethod = "DELETE",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "rid")
    @PreAuthorize("hasAuthority('delrole')")
    @DeleteMapping("/del/user")
    public ResponseResult delByRid(Integer rid){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            userService.delByRid(rid);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    /**
     * 获取角色统计数据
     * @return
     */
    @ApiOperation(value = "获取角色统计数据",notes = "获取角色统计数据",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/getreport")
    public ResponseResult getReport(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<ReportDto> report = userService.getReport();
            result.Success("查询成功",report);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }


    /**
     * 获取短信换绑手机号验证码
     * @param phone
     * @return
     */
    @ApiOperation(value = "获取短信换绑手机号验证码",notes = "获取短信换绑手机号验证码",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(value = "phone",required = true)
    @GetMapping("/smscodechangephone")
    public ResponseResult GetSmsCodeTwo(String phone){
        Integer validateCode = 0;
        ResponseResult<Object> result = new ResponseResult<>();
        try {
//            判断该电话是否已经绑定手机号了
            UserDto byPhone = userService.findByPhone(phone);
            if(byPhone != null){
                result.FAIL_PHONEALREADYUSE();
            }else {
//              随机生成4位数字验证码
                validateCode = ValidateCodeUtils.generateValidateCode(4);
//              给用户发送验证码
                String string = validateCode.toString();
                String[] code = {string};
                String[] phones = {phone};
                SendSmsUtils.sendShortMessage("1438295",phones,code);
//              将验证码保存到redis
                jedisPool.getResource().setex(phone+ RedisMessageConstant.SENDTYPE_CHANGEPHONE,300, String.valueOf(validateCode));
                result.Success("发送验证码成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FailToSendCode("发送验证码失败");
        }
        return result;
    }

    /**
     * 修改密码
     * @param userVo
     * @return
     */
    @ApiOperation(value = "修改密码",notes = "修改密码",httpMethod = "PUT",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = UserVo.class,required = true,value = "userVo")
//    @PreAuthorize("hasAuthority('upduser')")
    @PutMapping("/updpwd")
    public ResponseResult updpwd(@RequestBody UserVo userVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            UserDto userDto = UserCovert.INSTANCE.vo2dto(userVo);
            String encode = passwordEncoder.encode(userDto.getUserPassword());
            userDto.setUserPassword(encode);
            UserDto upd = userService.upd(userDto);
            result.Success("更新成功",upd);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_UPDATE();
        }
        return result;
    }


    /**
     * 获取注册验证码
     * @param phone
     * @return
     */
    @ApiOperation(value = "获取注册验证码",notes = "获取注册验证码",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(value = "phone",required = true)
    @GetMapping("/smscodefour")
    public ResponseResult GetSmsCodeFour(String phone){
        Integer validateCode = 0;
        ResponseResult<Object> result = new ResponseResult<>();
        try {
//            判断该电话是否已经绑定手机号了
            UserDto byPhone = userService.findByPhone(phone);
            if(byPhone != null){
                result.FAIL_PHONEALREADYUSE();
            }else {
//              随机生成4位数字验证码
                validateCode = ValidateCodeUtils.generateValidateCode(4);
//              给用户发送验证码
                String string = validateCode.toString();
                String[] code = {string};
                String[] phones = {phone};
                SendSmsUtils.sendShortMessage("1353289",phones,code);
//              将验证码保存到redis
                jedisPool.getResource().setex(phone+ RedisMessageConstant.SENDTYPE_ZHUCE,300, String.valueOf(validateCode));
                result.Success("发送验证码成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FailToSendCode("发送验证码失败");
        }
        return result;
    }

    /**
     * 用户注册
     * @param cat
     * @param userVo
     * @return
     */
    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST",response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataTypeClass = String.class, required = true, value = "cat"),
            @ApiImplicitParam(dataTypeClass = UserVo.class, required = true, value = "userVo"),
    })
    @PostMapping("/zhuce/{cat}")
    public ResponseResult zhuCe(@PathVariable("cat") String cat,@RequestBody UserVo userVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String code = jedisPool.getResource().get(userVo.getUserPhone() + RedisMessageConstant.SENDTYPE_ZHUCE);
            if(cat.equals(code) && code != null && cat != null){
                String userName = userVo.getUserName();
                UserDto byUserName = userService.findByUserName(userName);
                if(byUserName != null){
                    result.FAIL_NAMEALREDYUSE();
                }else {
                    UserDto userDto = UserCovert.INSTANCE.vo2dto(userVo);
                    LocalDate now = LocalDate.now();
                    userDto.setUserCreatedate(now);
                    String encode = passwordEncoder.encode(userDto.getUserPassword());
                    userDto.setUserPassword(encode);
                    userDto.setRoleId(3);
                    UserDto add = userService.add(userDto);
                    result.Success("注册成功",add);
                }
            }else {
                result.FAIL_CODEERROR();
            }
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    /**
     * 发送重置后密码
     * @param phone
     * @return
     */
    @ApiOperation(value = "发送重置后密码",notes = "发送重置后密码",httpMethod = "POST",response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(dataTypeClass = String.class, required = true, value = "phone"),
            @ApiImplicitParam(dataTypeClass = String.class, required = true, value = "cat"),
    })
    @PostMapping("/updpwd")
    public ResponseResult updPwd(String phone,String cat){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String code1 = jedisPool.getResource().get(phone + RedisMessageConstant.SENDTYPE_FORGETPWD);
            if(cat.equals(code1) && code1 != null && cat != null){
                //        先重置密码
                // TODO: 2022/6/15
                //生成指定长度的随机字符串
                String str= RandomStringUtils.randomAlphanumeric(6);
                UserDto byPhone = userService.findByPhone(phone);
                String encode = passwordEncoder.encode(str);
                byPhone.setUserPassword(encode);
                UserDto upd = userService.upd(byPhone);
//        在发送短信验证码
                // TODO: 2022/6/15
//          给用户发送验证码
                String[] code = {str};
                String[] phones = {phone};
                SendSmsUtils.sendShortMessage("1440822",phones,code);
                result.Success("重置密码成功",upd);
            }else {
//            表明验证码输入错误
                result.FAIL_CODEERROR();
            }

        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }


    /**
     * 将用户修改为会员
     * @param userId
     * @return
     */
    @ApiOperation(value = "将用户修改为会员",notes = "将用户修改为会员",httpMethod = "POST",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class, required = true, value = "userId")
    @PostMapping("/changemem")
    public ResponseResult chengeRoleToMember(Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            UserDto byId = userService.findById(userId);
            byId.setRoleId(6);
            UserDto upd = userService.upd(byId);
            result.Success("恭喜成为会员",upd);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_UPDATE();
        }
        return result;
    }

    /**
     * 获取用户登录信息
     * @return
     * @throws JsonProcessingException
     */
    @ApiOperation(value = "获取用户登录信息",notes = "获取用户登录信息",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/getImg")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseResult test() throws JsonProcessingException {
        ResponseResult<Object> result = new ResponseResult<>();
        String principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        result.Success("ok!",principal);
        return result;
    }

}
