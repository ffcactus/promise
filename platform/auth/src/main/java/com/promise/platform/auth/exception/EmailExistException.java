package com.promise.platform.auth.exception;

import com.promise.platform.common.dto.ErrorResponseConverter;
import com.promise.platform.common.dto.ErrorResponseV1;

import java.util.ArrayList;


public class EmailExistException extends RuntimeException implements ErrorResponseConverter {

    /**
     *
     */
    private static final long serialVersionUID = -8114473858639916258L;
    public static final String errorCode = "api.error.message.auth.EmailExist";

    @Override
    public ErrorResponseV1 convertToErrorResponse() {
        return new ErrorResponseV1(errorCode, "This Email has already registered.", new ArrayList<>());
    }
}