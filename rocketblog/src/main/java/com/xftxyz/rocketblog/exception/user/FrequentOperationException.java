package com.xftxyz.rocketblog.exception.user;

public class FrequentOperationException extends RuntimeException {

    public FrequentOperationException() {
    }

    public FrequentOperationException(String arg0) {
        super(arg0);
    }

    public FrequentOperationException(Throwable arg0) {
        super(arg0);
    }

    public FrequentOperationException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FrequentOperationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
