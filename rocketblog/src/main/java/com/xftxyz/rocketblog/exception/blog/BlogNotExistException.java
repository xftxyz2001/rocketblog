package com.xftxyz.rocketblog.exception.blog;

import org.springframework.util.StringUtils;

import com.xftxyz.rocketblog.exception.RocketblogException;
import com.xftxyz.rocketblog.result.Result;
import com.xftxyz.rocketblog.result.ResultCode;

public class BlogNotExistException extends RocketblogException {

    public BlogNotExistException() {
    }

    public BlogNotExistException(String message) {
        super(message);
    }

    public BlogNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogNotExistException(Throwable cause) {
        super(cause);
    }

    public BlogNotExistException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<Object> handle() {
        Integer code = ResultCode.BLOG_NOT_EXIST.getCode();
        String message = StringUtils.hasLength(this.getMessage()) ? this.getMessage()
                : ResultCode.BLOG_NOT_EXIST.getMessage();
        return Result.error(code, message);
    }

}
