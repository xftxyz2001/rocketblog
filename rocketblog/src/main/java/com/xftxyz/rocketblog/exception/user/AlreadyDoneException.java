package com.xftxyz.rocketblog.exception.user;

import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.RocketblogException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class AlreadyDoneException extends RocketblogException {

    public AlreadyDoneException() {
    }

    public AlreadyDoneException(String message) {
        super(message);
    }

    public AlreadyDoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyDoneException(Throwable cause) {
        super(cause);
    }

    public AlreadyDoneException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<Object> handle() {
        Integer code = ResultCode.ALREADY_DONE.getCode();
        String message = StringUtils.hasLength(this.getMessage()) ? this.getMessage()
                : ResultCode.ALREADY_DONE.getMessage();
        return Result.error(code, message);
    }

}
