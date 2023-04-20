package com.xftxyz.rocketblog.exception.captcha;

import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.RocketblogException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class CaptchaErrorException extends RocketblogException {

    public CaptchaErrorException() {
    }

    public CaptchaErrorException(String message) {
        super(message);
    }

    public CaptchaErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaErrorException(Throwable cause) {
        super(cause);
    }

    public CaptchaErrorException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<Object> handle() {
        Integer code = ResultCode.CAPTCHA_ERROR.getCode();
        String message = StringUtils.hasLength(this.getMessage()) ? this.getMessage()
                : ResultCode.CAPTCHA_ERROR.getMessage();
        return Result.error(code, message);
    }

}
