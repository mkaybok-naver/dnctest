package com.dnc.dispatcher.rest.common.exception;

public class CRequestExistByDispatcher extends RuntimeException {

	public CRequestExistByDispatcher() {
	}

	public CRequestExistByDispatcher(String message) {
		super(message);
	}

	public CRequestExistByDispatcher(Throwable cause) {
		super(cause);
	}

	public CRequestExistByDispatcher(String message, Throwable cause) {
		super(message, cause);
	}

	public CRequestExistByDispatcher(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
