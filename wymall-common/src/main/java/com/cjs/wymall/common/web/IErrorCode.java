package com.cjs.wymall.common.web;

/**
 * @description: 封装web请求错误信息状态码
 * @author: cuijunsheng
 * @date: 2020/5/25
 */
public interface IErrorCode {

    long getCode();

    String getMessage();
}
