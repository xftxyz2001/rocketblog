package com.xftxyz.rocketblog.exception.captcha;

import com.xftxyz.rocketblog.exception.RocketblogException;

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

}
