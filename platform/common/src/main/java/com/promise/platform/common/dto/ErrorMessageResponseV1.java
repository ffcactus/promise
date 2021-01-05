package com.promise.platform.common.dto;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the general error message that is designed to be able to
 * translate.
 */
@Data
public class ErrorMessageResponseV1 {
    public static final ResponseEntity<ErrorMessageResponseV1> UNAUTHORIZED;
    public static final ResponseEntity<ErrorMessageResponseV1> BAD_REQUEST;
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

        ErrorMessageResponseV1 badrequestMessage = new ErrorMessageResponseV1();
        badrequestMessage.errorCode = "platform.error.badrequest";
        badrequestMessage.message = "Invalid request.";
        BAD_REQUEST = ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(responseHeaders).body(badrequestMessage);
        INTERNAL_SERVER_ERROR = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(responseHeaders).build();
    }

    public ErrorMessageResponseV1() {
        messageArgs = new ArrayList<String>();
    }
}
