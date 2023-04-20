package com.xftxyz.rocketblog.exception.captcha;

import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.RocketblogException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class FrequentOperationException extends RocketblogException {

    public FrequentOperationException() {
    }

    public FrequentOperationException(String message) {
        super(message);
    }

    public FrequentOperationException(Throwable cause) {
        super(cause);
    }

    public FrequentOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrequentOperationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<Object> handle() {
        Integer code = ResultCode.CAPTCHA_SEND_FREQUENTLY.getCode();
        String message = StringUtils.hasLength(this.getMessage()) ? this.getMessage()
                : ResultCode.CAPTCHA_SEND_FREQUENTLY.getMessage();
        return Result.error(code, message);
    }

}
