package com.xftxyz.rocketblog.exception.user;

import com.xftxyz.rocketblog.exception.RocketBlogException;

public class EmailOrPasswordErrorException extends RocketBlogException {

    public EmailOrPasswordErrorException() {
    }

    public EmailOrPasswordErrorException(String message) {
        super(message);
    }

    public EmailOrPasswordErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailOrPasswordErrorException(Throwable cause) {
        super(cause);
    }

    public EmailOrPasswordErrorException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
