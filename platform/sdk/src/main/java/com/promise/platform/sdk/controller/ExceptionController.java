package com.promise.platform.sdk.controller;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;

/**
 * The class contains the exception handling.
 *
 */
@Controller
public class ExceptionController
{
    @ExceptionHandler
    public ResponseEntity<ErrorMessageResponseV1> handle(Exception ex)
    {
        if (ex instanceof NoSuchElementException)
        {
            return ErrorMessageResponseV1.NOT_FOUND;
        }
        return ErrorMessageResponseV1.INTERNAL_SERVER_ERROR;
    }
}
