package com.dnc.dispatcher.rest.common.exception;

public class CEmailSignupFailedException extends RuntimeException {

	public CEmailSignupFailedException() {
		super();
	}

	public CEmailSignupFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CEmailSignupFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CEmailSignupFailedException(String message) {
		super(message);
	}

	public CEmailSignupFailedException(Throwable cause) {
		super(cause);
	}

}
