package com.masaiqi.exception;

/**
 * 业务异常
 *
 * @author <a href="mailto:masaiqi.com@gmail.com">masaiqi</a>
 * @date 2021/6/9 19:39
 */
public class BizException extends RuntimeException{

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode) {
        this.errorCode = errorCode;
    }

    public BizException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BizException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BizException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BizException() {
    }
}
