package com.dnc.dispatcher.rest.common.exception;

public class CUserNotFoundException extends RuntimeException {

	public CUserNotFoundException() {
		super();
	}

	public CUserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CUserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CUserNotFoundException(String message) {
		super(message);
	}

	public CUserNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
}
