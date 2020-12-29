package com.promise.platform.common.controller;


import com.promise.platform.common.dto.ErrorResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

public class CommonExceptionController {
    private static final Logger log = LoggerFactory.getLogger(CommonExceptionController.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseV1> noSuchElement(NoSuchElementException e) {
        log.warn("Internal server error", e);
        var errResp = new ErrorResponseV1("common.exception.no-such-element", "No such element.", null);
        return new ResponseEntity<>(errResp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseV1> internalException(Exception e) {
        log.warn("Internal server error", e);
        var errResp = new ErrorResponseV1("common.exception.internal", "Internal exception.", null);
        return new ResponseEntity<>(errResp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
