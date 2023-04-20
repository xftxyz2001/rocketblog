package com.xftxyz.rocketblog.exception.blog;

import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.RocketblogException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class EmailSendError extends RocketblogException {

    public EmailSendError() {
    }

    public EmailSendError(String message) {
        super(message);
    }

    public EmailSendError(Throwable cause) {
        super(cause);
    }

    public EmailSendError(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSendError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<Object> handle() {
        Integer code = ResultCode.EMAIL_SEND_ERROR.getCode();
        String message = StringUtils.hasLength(this.getMessage()) ? this.getMessage()
                : ResultCode.EMAIL_SEND_ERROR.getMessage();
        return Result.error(code, message);
    }

}
