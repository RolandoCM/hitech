package com.app.hitech.exceptions;

/**
 * 
 * @author Rolando C.
 *
 */
public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1394803713241081221L;

	public AppException(String message, Throwable trace) {
		super(message, trace);
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable trace) {
		super(trace);
	}
}
