package com.cashbookcloud.common.result;

public enum ResponseCode {
    SUCCESS(200,"请求成功"),
    DELETED(204,"删除成功"),
    BAD_REQUEST(400,"请求的地址不存在或者包含不支持的参数"),
    UNAUTHORIZED(401,"未授权"),
    FORBIDDEN(403,"被禁止访问"),
    NOT_FOUND(404,"请求的资源不存在"),
    NOT_LOGIN(405,"未登录"),
    FAIL_LOGIN(406,"登录失败"),
    LOGINTOOMANY(407,"超出登录数量限制"),
    ISNULL(408,"查询为null"),
    FAIL_QUERY(409,"查询失败"),
    FAIL_ADD(410,"添加失败"),
    FAIL_DELETE(411,"删除失败"),
    FAIL_UPDATE(412,"更新失败"),
    Unprocesable_entity(422,"[POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误"),
    INTERNAL_SERVER_ERROR(500,"内部错误"),
    FailToSendCode(421,"发送验证码失败");
    private Integer code;
    private String message;

    ResponseCode() {
    }

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
