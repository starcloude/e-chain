package com.echain.common.exceptions;

public class BusiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusiException() {
		super();
	}
	public BusiException(String message) {
		super(message);
	}

	public BusiException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusiException(Throwable cause) {
		super(cause);
	}

}
