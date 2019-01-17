package com.promise.platform.sdk.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

/**
 * Represents the general error message that is designed to be able to
 * translate.
 *
 */
@Data
public class ErrorMessageResponseV1
{
    public static final ResponseEntity<ErrorMessageResponseV1> NOT_FOUND = new ResponseEntity<ErrorMessageResponseV1>(
            HttpStatus.NOT_FOUND);
    public static final ResponseEntity<ErrorMessageResponseV1> INTERNAL_SERVER_ERROR = new ResponseEntity<ErrorMessageResponseV1>(
            HttpStatus.INTERNAL_SERVER_ERROR);
    public String errorCode;
    public String message;
    public List<String> messageArgs;

    public ErrorMessageResponseV1()
    {
        messageArgs = new ArrayList<String>();
    }
}
