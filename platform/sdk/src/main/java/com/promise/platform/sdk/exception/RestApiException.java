package com.promise.platform.sdk.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RestApiException should contains informations that can be translate to a REST
 * API error response payload.
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class RestApiException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = -21175707973265499L;

    private String id;
    private String message;
    private String solution;

    /**
     * Constructor.
     * 
     * @param key The unique id of this kind of exception.
     * @param message A short message tells what happened.
     * @param solution A short message tells how to solve this problem.
     */
    public RestApiException(String id, String message, String solution)
    {
        super();
        this.id = id;
        this.message = message;
        this.solution = solution;
    }

}
