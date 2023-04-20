package com.xftxyz.rocketblog.exception.chat;

import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.RocketblogException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class NoChatException extends RocketblogException {

    public NoChatException() {
    }

    public NoChatException(String message) {
        super(message);
    }

    public NoChatException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoChatException(Throwable cause) {
        super(cause);
    }

    public NoChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<Object> handle() {
        Integer code = ResultCode.NO_CHAT.getCode();
        String message = StringUtils.hasLength(this.getMessage()) ? this.getMessage()
                : ResultCode.NO_CHAT.getMessage();
        return Result.error(code, message);
    }

}
