package com.promise.platform.auth.exception;

import com.promise.platform.common.dto.ErrorResponseConverter;
import com.promise.platform.common.dto.ErrorResponseV1;

import java.util.ArrayList;

public class UserNotExistException extends RuntimeException implements ErrorResponseConverter {

    public static final String errorCode = "api.error.message.auth.UserNotExist";
    public static final String message = "This user does not exist.";

    @Override
    public ErrorResponseV1 convertToErrorResponse() {
        return new ErrorResponseV1(errorCode, "This username has already registered.", new ArrayList<>());
    }
}
