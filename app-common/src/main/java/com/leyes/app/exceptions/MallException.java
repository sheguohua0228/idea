package com.leyes.app.exceptions;



public class MallException extends CustomException {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1000001L;

	public MallException() {
		super();
	}

	public MallException(String message) {
		super(message);

	}
	public MallException(Throwable innerException){
		super(innerException);
	}
	public MallException(String message,Throwable innerException){
		super(message,innerException);
	}

	public MallException(String message, Object... args) {
		super(String.format(message, args));

	}
}
