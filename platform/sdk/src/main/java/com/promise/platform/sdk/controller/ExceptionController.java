package com.promise.platform.sdk.controller;

import java.util.NoSuchElementException;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mapping.model.MappingInstantiationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;
import com.promise.platform.sdk.exception.UnauthorizedException;

/**
 * The class contains the exception handling.
 *
 */
public class ExceptionController
{
    @ExceptionHandler
    public ResponseEntity<ErrorMessageResponseV1> handle(Exception ex) throws Exception
    {
        // If the exception is annotated with @ResponseStatus re-throw it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                    (ex.getClass(), ResponseStatus.class) != null)
          throw ex;
        
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
