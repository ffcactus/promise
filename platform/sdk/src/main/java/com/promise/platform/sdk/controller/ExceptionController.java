package com.promise.platform.sdk.controller;

import java.util.NoSuchElementException;

import org.springframework.data.mapping.model.MappingInstantiationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.exception.UnauthorizedException;

/**
 * The class contains the exception handling.
 *
 */
public class ExceptionController
{
    @ExceptionHandler
    public ResponseEntity<ErrorMessageResponseV1> handle(Exception ex)
    {
        if (ex instanceof NoSuchElementException)
        {
            return ErrorMessageResponseV1.NOT_FOUND;
        }
        if (ex instanceof UnauthorizedException) {
        	return ErrorMessageResponseV1.UNAUTHORIZED;
        }
        if (ex instanceof MappingInstantiationException) {
        	return ErrorMessageResponseV1.BAD_REQUEST;
        }
        return ErrorMessageResponseV1.INTERNAL_SERVER_ERROR;
    }
}
