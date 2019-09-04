package com.dnc.dispatcher.rest.common.exception;

public class CRequestExistByRequester extends RuntimeException {

	public CRequestExistByRequester() {
	}

	public CRequestExistByRequester(String message) {
		super(message);
	}

	public CRequestExistByRequester(Throwable cause) {
		super(cause);
	}

	public CRequestExistByRequester(String message, Throwable cause) {
		super(message, cause);
	}

	public CRequestExistByRequester(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
