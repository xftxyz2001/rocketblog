package com.xftxyz.rocketblog.exception;

import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class RocketblogException extends RuntimeException {

    public RocketblogException() {
    }

    public RocketblogException(String message) {
        super(message);
    }

    public RocketblogException(Throwable cause) {
        super(cause);
    }

    public RocketblogException(String message, Throwable cause) {
        super(message, cause);
    }

    public RocketblogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Result<Object> handle() {
        return Result.error(ResultCode.UNKNOWN_ERROR.getCode(), this.getMessage());
    }

}
