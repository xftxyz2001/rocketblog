package com.xftxyz.rocketblog.exception.blog;

public class BlogNotExistException extends RuntimeException {

    public BlogNotExistException() {
    }

    public BlogNotExistException(String message) {
        super(message);
    }

    public BlogNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogNotExistException(Throwable cause) {
        super(cause);
    }

    public BlogNotExistException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
