package com.leyes.app.exceptions;



public class EmployeeException extends CustomException {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1000001L;

	public EmployeeException() {
		super();
	}

	public EmployeeException(String message) {
		super(message);

	}
	public EmployeeException(Throwable innerException){
		super(innerException);
	}
	public EmployeeException(String message,Throwable innerException){
		super(message,innerException);
	}

	public EmployeeException(String message, Object... args) {
		super(String.format(message, args));

	}
}
