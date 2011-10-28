package org.yseasony.exam.exception;

public class SvcException extends RuntimeException {


	private static final long serialVersionUID = 3310515046831495825L;

	public SvcException() {
		super();
	}

	public SvcException(String message) {
		super(message);
	}

	public SvcException(Throwable cause) {
		super(cause);
	}

	public SvcException(String message, Throwable cause) {
		super(message, cause);
	}
}
