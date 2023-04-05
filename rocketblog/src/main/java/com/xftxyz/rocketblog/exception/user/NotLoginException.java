package com.xftxyz.rocketblog.exception.user;

import com.xftxyz.rocketblog.exception.RocketBlogException;

public class NotLoginException extends RocketBlogException {
    public NotLoginException() {
    }

    public NotLoginException(String message) {
        super(message);
    }

    public NotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotLoginException(Throwable cause) {
        super(cause);
    }

    public NotLoginException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
