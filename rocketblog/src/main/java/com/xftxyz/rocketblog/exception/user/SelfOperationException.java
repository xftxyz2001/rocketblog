package com.xftxyz.rocketblog.exception.user;

public class SelfOperationException extends RuntimeException {

    public SelfOperationException() {
    }

    public SelfOperationException(String message) {
        super(message);
    }

    public SelfOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelfOperationException(Throwable cause) {
        super(cause);
    }

    public SelfOperationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
