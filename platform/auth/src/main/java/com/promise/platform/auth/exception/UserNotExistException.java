package com.promise.platform.auth.exception;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.model.ErrorResponseConverter;

import java.util.ArrayList;

public class UserNotExistException extends RuntimeException implements ErrorResponseConverter {

    public static final String errorCode = "api.error.message.auth.UserNotExist";
    public static final String message = "This user does not exist.";

    @Override
    public ErrorMessageResponseV1 convertToErrorResponse() {
        var ret = new ErrorMessageResponseV1();
        ret.setErrorCode(errorCode);
        ret.setMessage(message);
        ret.setMessageArgs( new ArrayList<String>());
        return ret;
    }
}
