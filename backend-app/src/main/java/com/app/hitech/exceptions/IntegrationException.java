package com.app.hitech.exceptions;

/**
 * <h1>Integration Exception</h1> Definition exception of integration extends
 * from {@link AppException}
 * 
 * 
 * @author Rolando Castillo
 *
 */
public class IntegrationException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5747613822132271550L;

	public IntegrationException(String message, Throwable trace) {
		super(message, trace);
	}

}
