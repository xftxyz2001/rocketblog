package com.xftxyz.rocketblog.exception.user;

import com.xftxyz.rocketblog.exception.RocketBlogException;

public class AlreadyDoneException extends RocketBlogException {

    public AlreadyDoneException() {
    }

    public AlreadyDoneException(String message) {
        super(message);
    }

    public AlreadyDoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyDoneException(Throwable cause) {
        super(cause);
    }

    public AlreadyDoneException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
