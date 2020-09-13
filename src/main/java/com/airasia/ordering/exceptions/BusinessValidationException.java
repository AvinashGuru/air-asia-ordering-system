package com.airasia.ordering.exceptions;

/**
 * @author Avinash Gurumurthy
 * 
 * This class is to generate Exceptions thrown by service layer upon failure validations
 *
 */
public class BusinessValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	
	private String type;
	
	public BusinessValidationException() {
		super();
	}

	public BusinessValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessValidationException(String message) {
		super(message);
		this.setMessage(message);
	}

	public BusinessValidationException(Throwable cause) {
		super(cause);
	}
	
	public String getMessage() {
        return message;
    }
	
	private void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
