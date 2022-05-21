package com.cashbookcloud.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel(description = "统一返回数据")
@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ResponseResult<T> implements Serializable {
    @ApiModelProperty(value = "返回参数",required = false)
    private T data;
    @ApiModelProperty(value = "返回数据",required = true)
    private Meta meta;

    public ResponseResult(T data, Meta meta) {
        this.data = data;
        this.meta = meta;
    }

    public ResponseResult() {

        this.meta=new Meta();
    }


//    200OK请求成功
    public void Success(){
        meta.setMsg(ResponseCode.SUCCESS.getMessage());
        meta.setStatus(ResponseCode.SUCCESS.getCode());
    }

    public void Success(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.SUCCESS.getCode());
    }

    public void Success(String msg,T data){
        meta.setStatus(ResponseCode.SUCCESS.getCode());
        meta.setMsg(msg);
        this.data=data;
    }


//    FAIL_ADD
    public void FAIL_ADD(){
        meta.setMsg(ResponseCode.FAIL_ADD.getMessage());
        meta.setStatus(ResponseCode.FAIL_ADD.getCode());
    }


    public void FAIL_ADD(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.FAIL_ADD.getCode());
    }

    public void FAIL_ADD(String msg,T data){
        meta.setStatus(ResponseCode.FAIL_ADD.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    204DELETED删除成功

    public void Delete(){
        meta.setMsg(ResponseCode.DELETED.getMessage());
        meta.setStatus(ResponseCode.DELETED.getCode());
    }


    public void Delete(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.DELETED.getCode());
    }

    public void Delete(String msg,T data){
        meta.setStatus(ResponseCode.DELETED.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    400BAD REQUEST请求的地址不存在或者包含不支持的参数

    public void BadRequest(){
        meta.setMsg(ResponseCode.BAD_REQUEST.getMessage());
        meta.setStatus(ResponseCode.BAD_REQUEST.getCode());
    }


    public void BadRequest(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.BAD_REQUEST.getCode());
    }

    public void BadRequest(String msg,T data){
        meta.setStatus(ResponseCode.BAD_REQUEST.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    401UNAUTHORIZED未授权

    public void Unauthorized(){
        meta.setMsg(ResponseCode.UNAUTHORIZED.getMessage());
        meta.setStatus(ResponseCode.UNAUTHORIZED.getCode());
    }

    public void Unauthorized(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.UNAUTHORIZED.getCode());
    }

    public void Unauthorized(String msg,T data){
        meta.setStatus(ResponseCode.UNAUTHORIZED.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    403FORBIDDEN被禁止访问

    public void Forbidden(){
        meta.setMsg(ResponseCode.FORBIDDEN.getMessage());
        meta.setStatus(ResponseCode.FORBIDDEN.getCode());
    }


    public void Forbidden(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.FORBIDDEN.getCode());
    }

    public void Forbidden(String msg,T data){
        meta.setStatus(ResponseCode.FORBIDDEN.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    404NOT FOUND请求的资源不存在

    public void Not_found(){
        meta.setMsg(ResponseCode.NOT_FOUND.getMessage());
        meta.setStatus(ResponseCode.NOT_FOUND.getCode());
    }

    public void Not_found(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.NOT_FOUND.getCode());
    }

    public void Not_found(String msg,T data){
        meta.setStatus(ResponseCode.NOT_FOUND.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    422Unprocesable entity[POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误

    public void Unprocesable_entity(){
        meta.setMsg(ResponseCode.Unprocesable_entity.getMessage());
        meta.setStatus(ResponseCode.Unprocesable_entity.getCode());
    }


    public void Unprocesable_entity(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.Unprocesable_entity.getCode());
    }

    public void Unprocesable_entity(String msg,T data){
        meta.setStatus(ResponseCode.Unprocesable_entity.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    500INTERNAL SERVER ERROR内部错误

    public void Internal_server_error(){
        meta.setMsg(ResponseCode.INTERNAL_SERVER_ERROR.getMessage());
        meta.setStatus(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
    }


    public void Internal_server_error(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
    }

    public void Internal_server_error(String msg,T data){
        meta.setStatus(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    FailToSendCode(421,"发送验证码失败");

    public void FailToSendCode(){
        meta.setMsg(ResponseCode.FailToSendCode.getMessage());
        meta.setStatus(ResponseCode.FailToSendCode.getCode());
    }


    public void FailToSendCode(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.FailToSendCode.getCode());
    }

    public void FailToSendCode(String msg,T data){
        meta.setStatus(ResponseCode.FailToSendCode.getCode());
        meta.setMsg(msg);
        this.data=data;
    }
//    NOT_LOGIN
    public void NOT_LOGIN(){
        meta.setMsg(ResponseCode.NOT_LOGIN.getMessage());
        meta.setStatus(ResponseCode.NOT_LOGIN.getCode());
    }


    public void NOT_LOGIN(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.NOT_LOGIN.getCode());
    }

    public void NOT_LOGIN(String msg,T data){
        meta.setStatus(ResponseCode.NOT_LOGIN.getCode());
        meta.setMsg(msg);
        this.data=data;
    }
//    FAIL_LOGIN

    public void FAIL_LOGIN(){
        meta.setMsg(ResponseCode.FAIL_LOGIN.getMessage());
        meta.setStatus(ResponseCode.FAIL_LOGIN.getCode());
    }


    public void FAIL_LOGIN(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.FAIL_LOGIN.getCode());
    }

    public void FAIL_LOGIN(String msg,T data){
        meta.setStatus(ResponseCode.FAIL_LOGIN.getCode());
        meta.setMsg(msg);
        this.data=data;
    }
//    LOGINTOOMANY
    public void LOGINTOOMANY(){
        meta.setMsg(ResponseCode.LOGINTOOMANY.getMessage());
        meta.setStatus(ResponseCode.LOGINTOOMANY.getCode());
    }


    public void LOGINTOOMANY(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.LOGINTOOMANY.getCode());
    }

    public void LOGINTOOMANY(String msg,T data){
        meta.setStatus(ResponseCode.LOGINTOOMANY.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    ISNULL

    public void ISNULL(){
        meta.setMsg(ResponseCode.ISNULL.getMessage());
        meta.setStatus(ResponseCode.ISNULL.getCode());
    }


    public void ISNULL(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.ISNULL.getCode());
    }

    public void ISNULL(String msg,T data){
        meta.setStatus(ResponseCode.ISNULL.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    FAIL_QUERY

    public void FAIL_QUERY(){
        meta.setMsg(ResponseCode.FAIL_QUERY.getMessage());
        meta.setStatus(ResponseCode.FAIL_QUERY.getCode());
    }


    public void FAIL_QUERY(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.FAIL_QUERY.getCode());
    }

    public void FAIL_QUERY(String msg,T data){
        meta.setStatus(ResponseCode.FAIL_QUERY.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

    //    FAIL_DELETE

    public void FAIL_DELETE(){
        meta.setMsg(ResponseCode.FAIL_DELETE.getMessage());
        meta.setStatus(ResponseCode.FAIL_DELETE.getCode());
    }


    public void FAIL_DELETE(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.FAIL_DELETE.getCode());
    }

    public void FAIL_DELETE(String msg,T data){
        meta.setStatus(ResponseCode.FAIL_DELETE.getCode());
        meta.setMsg(msg);
        this.data=data;
    }

//    FAIL_UPDATE

    public void FAIL_UPDATE(){
        meta.setMsg(ResponseCode.FAIL_UPDATE.getMessage());
        meta.setStatus(ResponseCode.FAIL_UPDATE.getCode());
    }


    public void FAIL_UPDATE(String msg){
        meta.setMsg(msg);
        meta.setStatus(ResponseCode.FAIL_UPDATE.getCode());
    }

    public void FAIL_UPDATE(String msg,T data){
        meta.setStatus(ResponseCode.FAIL_UPDATE.getCode());
        meta.setMsg(msg);
        this.data=data;
    }
}
