package com.promise.platform.devicebasic.exception;

/**
 * Indicate the case that there is no collector can perform the action.
 */
public class NoCollectorException extends Exception {
    public NoCollectorException() {
        super();
    }

    public NoCollectorException(String msg) {
        super(msg);
    }
}
