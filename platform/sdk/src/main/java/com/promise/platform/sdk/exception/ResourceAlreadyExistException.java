package com.promise.platform.sdk.exception;

import java.util.ArrayList;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.model.ErrorResponseConverter;

public class ResourceAlreadyExistException extends RuntimeException implements ErrorResponseConverter
{

    /**
     * 
     */
    private static final long serialVersionUID = 6042526327078612687L;

    private static final String errorCode = "api.error.message.common.ResourceAlreadyExist";

    @Override
    public ErrorMessageResponseV1 convertToErrorResponse()
    {
        var ret = new ErrorMessageResponseV1();
        ret.errorCode = errorCode;
        ret.messageArgs = new ArrayList<String>();
        return ret;
    }
}
