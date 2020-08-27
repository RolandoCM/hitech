package com.app.hitech.exceptions;

/**
 * <h1>Bussiness Exception</h1> Definition of Bussiness exception extends of
 * {@link AppException}
 * 
 * @author Rolando Castillo
 *
 */
public class BusinessException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429920643864476789L;

	public BusinessException(String message, Throwable trace) {
		super(message, trace);
	}

}
