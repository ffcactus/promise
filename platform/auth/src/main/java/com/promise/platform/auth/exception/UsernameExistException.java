package com.promise.platform.auth.exception;

import java.util.ArrayList;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.model.ErrorResponseConverter;


public class UsernameExistException extends RuntimeException implements ErrorResponseConverter
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8114473858639916258L;
	public static final String errorCode = "api.error.message.auth.UsernameExist";

    @Override
    public ErrorMessageResponseV1 convertToErrorResponse()
    {
        var ret = new ErrorMessageResponseV1();
        ret.errorCode = errorCode;
        ret.message = "This username has already registered.";
        ret.messageArgs = new ArrayList<String>();
        return ret;
    }
}