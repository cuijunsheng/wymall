package com.cjs.wymall.common.web;

/**
 * @description: 响应状态码枚举类
 * @author: cuijunsheng
 * @date: 2020/5/24
 */
public enum StatusCode implements IErrorCode{
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    StatusCode(long code, String message){
        this.code=code;
        this.message=message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
