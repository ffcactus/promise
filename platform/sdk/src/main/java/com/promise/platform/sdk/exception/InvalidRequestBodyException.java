package com.promise.platform.sdk.exception;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.model.ErrorResponseConverter;

import java.util.ArrayList;

public class InvalidRequestBodyException extends RuntimeException implements ErrorResponseConverter {

    /**
     *
     */
    private static final long serialVersionUID = 5963705519885587624L;

    public static final String errorCode = "api.error.message.common.InvalidRequestBody";

    @Override
    public ErrorMessageResponseV1 convertToErrorResponse() {
        var ret = new ErrorMessageResponseV1();
        ret.errorCode = errorCode;
        ret.message = "Invalid request body.";
        ret.messageArgs = new ArrayList<String>();
        return ret;
    }
}
