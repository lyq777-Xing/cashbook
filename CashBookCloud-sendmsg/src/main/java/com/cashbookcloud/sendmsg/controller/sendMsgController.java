package com.cashbookcloud.sendmsg.controller;

import com.cashbookcloud.common.constant.RedisMessageConstant;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.common.utils.SendSmsUtils;
import com.cashbookcloud.common.utils.ValidateCodeUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Date;

@RestController
public class sendMsgController {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取短信登录验证码
     * @param phone
     * @return
     */
    @ApiOperation("获取短信登录验证码")
    @ApiImplicitParam(value = "phone",required = true)
    @GetMapping("/smscodelogin")
    public ResponseResult GetSmsCode(String phone){
        Integer validateCode = 0;
        ResponseResult<Object> result = new ResponseResult<>();
        try {
//          随机生成4位数字验证码
            validateCode = ValidateCodeUtils.generateValidateCode(4);
//          给用户发送验证码
            String string = validateCode.toString();
            String[] code = {string};
            String[] phones = {phone};
            SendSmsUtils.sendShortMessage("1353292",phones,code);
        }catch (Exception e){
            e.printStackTrace();
            result.FailToSendCode("发送验证码失败");
        }
//        将验证码保存到redis
        jedisPool.getResource().setex(phone+ RedisMessageConstant.SENDTYPE_LOGIN,300, String.valueOf(validateCode));
        result.Success("发送验证码成功");
        System.out.println(validateCode);
        return result;
    }

    /**
     * 获取修改密码手机号验证码
     * @param phone
     * @return
     */
    @ApiOperation("获取修改密码验证码")
    @ApiImplicitParam(value = "phone",required = true)
    @GetMapping("/smscodechangepwd")
    public ResponseResult GetSmsCodeThree(String phone){
        Integer validateCode = 0;
        ResponseResult<Object> result = new ResponseResult<>();
        try {
//            判断该电话是否已经绑定手机号了

//          随机生成4位数字验证码
            validateCode = ValidateCodeUtils.generateValidateCode(4);
//          给用户发送验证码
            String string = validateCode.toString();
            String[] code = {string};
            String[] phones = {phone};
            SendSmsUtils.sendShortMessage("1438107",phones,code);
        }catch (Exception e){
            e.printStackTrace();
            result.FailToSendCode("发送验证码失败");
        }
//        将验证码保存到redis
        jedisPool.getResource().setex(phone+ RedisMessageConstant.SENDTYPE_CHANGEPWD,300, String.valueOf(validateCode));
        result.Success("发送验证码成功");
        System.out.println(validateCode);
        return result;
    }

    @ApiOperation("校验换绑手机号验证码是否正确")
    @GetMapping("/checkchangephone")
    public ResponseResult checkChangePhone(String phoneNumber,String cat){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String code = jedisPool.getResource().get(phoneNumber + RedisMessageConstant.SENDTYPE_CHANGEPHONE);
            if(cat.equals(code) && code != null && cat != null){
                //验证码输入正确
                result.Success("验证码正确");
            }else {
//            表明验证码输入错误
                result.FAIL_CODEERROR();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation("校验修改密码验证码是否正确")
    @GetMapping("/checkchangepwd")
    public ResponseResult checkChangePwd(String phoneNumber,String cat){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            String code = jedisPool.getResource().get(phoneNumber + RedisMessageConstant.SENDTYPE_CHANGEPWD);
            if(cat.equals(code) && code != null && cat != null){
                //验证码输入正确
                result.Success("验证码正确");
            }else {
//            表明验证码输入错误
                result.FAIL_CODEERROR();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
