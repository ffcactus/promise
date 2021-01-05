package com.promise.platform.common.exception;

import com.promise.platform.common.dto.ErrorResponseConverter;
import com.promise.platform.common.dto.ErrorResponseV1;

import java.util.ArrayList;

public class ResourceAlreadyExistException extends RuntimeException implements ErrorResponseConverter {

    /**
     *
     */
    private static final long serialVersionUID = 6042526327078612687L;

    public static final String errorCode = "api.error.message.common.ResourceAlreadyExist";

    @Override
    public ErrorResponseV1 convertToErrorResponse() {
        return new ErrorResponseV1(errorCode, "Resource already exist.", new ArrayList<>());
    }
}
