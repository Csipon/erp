package com.csipon.erp.exception;

public class UserEntryAlreadyExist extends Exception {

    public UserEntryAlreadyExist() {
        super();
    }

    public UserEntryAlreadyExist(String message) {
        super(message);
    }

    public UserEntryAlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEntryAlreadyExist(Throwable cause) {
        super(cause);
    }

    protected UserEntryAlreadyExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
