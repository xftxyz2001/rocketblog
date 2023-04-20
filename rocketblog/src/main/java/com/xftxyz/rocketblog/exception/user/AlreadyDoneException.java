package com.xftxyz.rocketblog.exception.user;

import com.xftxyz.rocketblog.exception.RocketblogException;

public class AlreadyDoneException extends RocketblogException {

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
