package com.xftxyz.rocketblog.exception.user;

import com.xftxyz.rocketblog.exception.RocketblogException;

public class EmailOrPasswordErrorException extends RocketblogException {

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
