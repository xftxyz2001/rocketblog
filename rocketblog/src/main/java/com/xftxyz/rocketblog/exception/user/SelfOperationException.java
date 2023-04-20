package com.xftxyz.rocketblog.exception.user;

import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.RocketblogException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class SelfOperationException extends RocketblogException {

    public SelfOperationException() {
    }

    public SelfOperationException(String message) {
        super(message);
    }

    public SelfOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelfOperationException(Throwable cause) {
        super(cause);
    }

    public SelfOperationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<Object> handle() {
        Integer code = ResultCode.SELF_OPERATION.getCode();
        String message = StringUtils.hasLength(this.getMessage()) ? this.getMessage()
                : ResultCode.SELF_OPERATION.getMessage();
        return Result.error(code, message);
    }

}
