package com.xftxyz.rocketblog.exception.image;

import com.xftxyz.rocketblog.exception.RocketBlogException;

public class ImageException extends RocketBlogException {

    private static final long serialVersionUID = 1L;

    public ImageException(String message) {
        super(message);
    }

    public ImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageException(Throwable cause) {
        super(cause);
    }

    public ImageException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
