package com.promise.platform.sdk.exception;

public class LoginFailureException extends RestApiException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7853907266464555157L;
	
	private static final String errorCode = "api.error.message.common.LoginFailure";

	public LoginFailureException() {
		super(errorCode, "Login failure.", "Provide the correct credential and try again.");
	}

}
