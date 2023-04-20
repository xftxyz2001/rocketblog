package com.xftxyz.rocketblog.exception.blog;

import com.xftxyz.rocketblog.exception.RocketblogException;

public class EmailSendError extends RocketblogException {

    public EmailSendError() {
    }

    public EmailSendError(String arg0) {
        super(arg0);
    }

    public EmailSendError(Throwable arg0) {
        super(arg0);
    }

    public EmailSendError(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public EmailSendError(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
