package com.promise.platform.common.exception;

import com.promise.platform.common.dto.ErrorResponseArgumentV1;

import java.util.List;

public class PromiseException extends Exception {
    private final String errorCode;
    private final String reason;
    private final List<ErrorResponseArgumentV1> arguments;

    public PromiseException(String errorCode, String reason, List<ErrorResponseArgumentV1> arguments, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.reason = reason;
        this.arguments = arguments;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getReason() {
        return reason;
    }

    public List<ErrorResponseArgumentV1> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return errorCode;
    }
}
