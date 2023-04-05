package com.xftxyz.rocketblog.exception.user;

import com.xftxyz.rocketblog.exception.RocketBlogException;

public class CaptchaErrorException extends RocketBlogException {

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

}
