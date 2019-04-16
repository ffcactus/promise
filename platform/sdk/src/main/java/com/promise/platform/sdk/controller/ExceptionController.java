package com.promise.platform.sdk.controller;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.exception.LoginFailureException;

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
        if (ex instanceof LoginFailureException) {
        	return ErrorMessageResponseV1.UNAUTHORIZED;
        }
        return ErrorMessageResponseV1.INTERNAL_SERVER_ERROR;
    }
}
