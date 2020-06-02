package com.cjs.wymall.common.web;

/**
 * @description: 通用返回结果
 * @author: cuijunsheng
 * @date: 2020/5/24
 */
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 成功返回结果
     *
     * @param data 返回的数据
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {

        return new CommonResult<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param message 提示消息
     * @param data    返回的数据
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(String message, T data) {

        return new CommonResult<>(StatusCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed() {

        return new CommonResult<>(StatusCode.FAILED.getCode(), StatusCode.FAILED.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(String message) {

        return new CommonResult<>(StatusCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {

        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误消息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode, String message) {

        return new CommonResult<>(errorCode.getCode(), message, null);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed() {

        return new CommonResult<>(StatusCode.VALIDATE_FAILED.getCode(), StatusCode.VALIDATE_FAILED.getMessage(), null);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示消息
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed(String message) {

        return new CommonResult<>(StatusCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录或token已经过期返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized() {

        return new CommonResult<>(StatusCode.UNAUTHORIZED.getCode(), StatusCode.UNAUTHORIZED.getMessage(), null);
    }

    /**
     * 未登录或token已经过期返回结果
     *
     * @param data 返回数据
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(T data) {

        return new CommonResult<>(StatusCode.UNAUTHORIZED.getCode(), StatusCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden() {

        return new CommonResult<>(StatusCode.FORBIDDEN.getCode(), StatusCode.FORBIDDEN.getMessage(), null);
    }

    /**
     * 未授权返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden(T data) {

        return new CommonResult<>(StatusCode.FORBIDDEN.getCode(), StatusCode.FORBIDDEN.getMessage(), data);
    }


    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
