package com.leyes.app.exceptions;



public class ComsystemException extends CustomException {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1000001L;

	public ComsystemException() {
		super();
	}

	public ComsystemException(String message) {
		super(message);

	}
	public ComsystemException(Throwable innerException){
		super(innerException);
	}
	public ComsystemException(String message,Throwable innerException){
		super(message,innerException);
	}

	public ComsystemException(String message, Object... args) {
		super(String.format(message, args));

	}
}
