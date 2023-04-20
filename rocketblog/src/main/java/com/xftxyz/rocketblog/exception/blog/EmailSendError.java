package com.xftxyz.rocketblog.exception.blog;

import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.RocketblogException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class EmailSendError extends RocketblogException {

    public EmailSendError() {
    }

    public EmailSendError(String arg0) {
        super(arg0);
    }

    public EmailSendError(Throwable arg0) {
        super(arg0);
    }

    public EmailSendError(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public EmailSendError(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    @Override
    public Result<Object> handle() {
        Integer code = ResultCode.EMAIL_SEND_ERROR.getCode();
        String message = StringUtils.hasLength(this.getMessage()) ? this.getMessage()
                : ResultCode.EMAIL_SEND_ERROR.getMessage();
        return Result.error(code, message);
    }

}
