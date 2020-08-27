package com.app.hitech.exceptions;

/**
 * @author Rolando C.
 *
 */
public enum MessageException {

	MESSAGE_NO_DATA("B01", "No data"), ERROR_BAD_INSERT("B02", "It has been bad to insert the new employee"),
	ERROR_BAD_UPDATE("B03", "It has been bad to update the employee with code: "),
	ERROR_NO_FOUND("B04", "Data does not exist");

	private final String description;
	private final String code;

	MessageException(String code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String toString() {
		return code.concat(" - ").concat(description);
	}

}
