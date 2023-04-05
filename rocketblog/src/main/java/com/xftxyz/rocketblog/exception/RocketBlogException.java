package com.xftxyz.rocketblog.exception;

public class RocketBlogException extends RuntimeException{
    public RocketBlogException() {
    }

    public RocketBlogException(String message) {
        super(message);
    }

    public RocketBlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public RocketBlogException(Throwable cause) {
        super(cause);
    }

    public RocketBlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
