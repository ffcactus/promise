package com.promise.platform.auth.sdk.exception;

import com.promise.platform.common.dto.ErrorResponseConverter;
import com.promise.platform.common.dto.ErrorResponseV1;

import java.util.ArrayList;

public class UnauthorizedException extends RuntimeException implements ErrorResponseConverter {

    /**
     *
     */
    private static final long serialVersionUID = 7853907266464555157L;

    public static final String errorCode = "api.error.message.common.unauthorized";

    @Override
    public ErrorResponseV1 convertToErrorResponse() {
        return new ErrorResponseV1(errorCode, "Unauthorized.", new ArrayList<>());
    }
}
