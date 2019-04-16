package com.promise.platform.sdk.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	public static final ResponseEntity<ErrorMessageResponseV1> UNAUTHORIZED;
    public static final ResponseEntity<ErrorMessageResponseV1> NOT_FOUND;
    public static final ResponseEntity<ErrorMessageResponseV1> INTERNAL_SERVER_ERROR;
    public String errorCode;
    public String message;
    public List<String> messageArgs;

    static {
    	var responseHeaders = new HttpHeaders();
    	responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
    	UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
    	NOT_FOUND = ResponseEntity.status(HttpStatus.NOT_FOUND).headers(responseHeaders).build();
    	INTERNAL_SERVER_ERROR = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
    	
    }
    public ErrorMessageResponseV1()
    {
        messageArgs = new ArrayList<String>();
    }
}
