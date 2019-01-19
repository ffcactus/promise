package com.promise.platform.sdk.exception;

public class InvalidRequestBodyException extends RestApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5963705519885587624L;
	
	private static final String errorCode = "api.error.message.common.InvalidRequestBody";

	public InvalidRequestBodyException() {
		super(errorCode, "Invalid request body.", "Fix the request body and try again.");
	}

}
