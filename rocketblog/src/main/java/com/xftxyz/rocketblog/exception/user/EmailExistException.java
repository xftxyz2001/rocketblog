package com.xftxyz.rocketblog.exception.user;

public class EmailExistException extends RuntimeException {

    public EmailExistException() {
    }

    public EmailExistException(String message) {
        super(message);
    }

    public EmailExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailExistException(Throwable cause) {
        super(cause);
    }

    public EmailExistException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
