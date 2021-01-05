package com.promise.platform.common.exception;

import com.promise.platform.common.dto.ErrorResponseConverter;
import com.promise.platform.common.dto.ErrorResponseV1;

import java.util.ArrayList;

public class ResourceNotFoundException extends RuntimeException implements ErrorResponseConverter {
    /**
     *
     */
    private static final long serialVersionUID = -780372475005439786L;
    public static final String errorCode = "api.error.message.common.ResourceNotFound";

    @Override
    public ErrorResponseV1 convertToErrorResponse() {
        return new ErrorResponseV1(errorCode, "Can't find the resource.", new ArrayList<>());
    }
}
