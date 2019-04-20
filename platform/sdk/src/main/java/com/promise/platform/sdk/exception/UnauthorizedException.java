package com.promise.platform.sdk.exception;

public class UnauthorizedException extends RestApiException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7853907266464555157L;
	
	private static final String errorCode = "api.error.message.common.unauthorized";

	public UnauthorizedException() {
		super(errorCode, "Unauthorized", "Provide the correct credential and try again.");
	}

}
