package com.promise.platform.common.exception;

import com.promise.platform.common.dto.ErrorResponseConverter;
import com.promise.platform.common.dto.ErrorResponseV1;

import java.util.ArrayList;

public class InvalidRequestBodyException extends RuntimeException implements ErrorResponseConverter {

    /**
     *
     */
    private static final long serialVersionUID = 5963705519885587624L;

    public static final String errorCode = "api.error.message.common.InvalidRequestBody";

    @Override
    public ErrorResponseV1 convertToErrorResponse() {
        return new ErrorResponseV1(errorCode, "Invalid request body.", new ArrayList<>());
    }
}
