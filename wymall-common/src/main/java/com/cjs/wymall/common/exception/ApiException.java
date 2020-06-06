package com.cjs.wymall.common.exception;

import com.cjs.wymall.common.web.IErrorCode;

/**
 * @description: 自定义业务层异常
 * @author: cuijunsheng
 * @date: 2020/6/6
 */
public class ApiException extends RuntimeException{
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}