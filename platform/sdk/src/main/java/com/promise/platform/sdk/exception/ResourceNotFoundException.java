package com.promise.platform.sdk.exception;

import java.util.ArrayList;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.model.ErrorResponseConverter;

public class ResourceNotFoundException  extends RuntimeException implements ErrorResponseConverter
{
    /**
     * 
     */
    private static final long serialVersionUID = -780372475005439786L;
    public static final String errorCode = "api.error.message.common.ResourceNotFound";

    @Override
    public ErrorMessageResponseV1 convertToErrorResponse()
    {
        var ret = new ErrorMessageResponseV1();
        ret.errorCode = errorCode;
        ret.message = "Can't find the resource.";
        ret.messageArgs = new ArrayList<String>();
        return ret;
    }
}
