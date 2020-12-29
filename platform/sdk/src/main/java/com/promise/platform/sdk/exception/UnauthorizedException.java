package com.promise.platform.sdk.exception;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.model.ErrorResponseConverter;

import java.util.ArrayList;

public class UnauthorizedException extends RuntimeException implements ErrorResponseConverter {

    /**
     *
     */
    private static final long serialVersionUID = 7853907266464555157L;

    public static final String errorCode = "api.error.message.common.unauthorized";

    @Override
    public ErrorMessageResponseV1 convertToErrorResponse() {
        var ret = new ErrorMessageResponseV1();
        ret.errorCode = errorCode;
        ret.message = "Unauthorized.";
        ret.messageArgs = new ArrayList<String>();
        return ret;
    }
}
