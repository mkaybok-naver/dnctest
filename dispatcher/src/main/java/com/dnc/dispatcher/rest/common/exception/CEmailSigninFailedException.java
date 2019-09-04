package com.dnc.dispatcher.rest.common.exception;

public class CEmailSigninFailedException extends RuntimeException {

	public CEmailSigninFailedException() {
		super();
	}

	public CEmailSigninFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CEmailSigninFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CEmailSigninFailedException(String message) {
		super(message);
	}

	public CEmailSigninFailedException(Throwable cause) {
		super(cause);
	}

}
