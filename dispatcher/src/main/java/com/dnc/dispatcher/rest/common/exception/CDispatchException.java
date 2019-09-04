package com.dnc.dispatcher.rest.common.exception;

public class CDispatchException extends RuntimeException {

	public CDispatchException() {
	}

	public CDispatchException(String message) {
		super(message);
	}

	public CDispatchException(Throwable cause) {
		super(cause);
	}

	public CDispatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public CDispatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
