package com.leyes.app.exceptions;



public class PrintException extends CustomException {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1000001L;

	public PrintException() {
		super();
	}

	public PrintException(String message) {
		super(message);

	}
	public PrintException(Throwable innerException){
		super(innerException);
	}
	public PrintException(String message,Throwable innerException){
		super(message,innerException);
	}

	public PrintException(String message, Object... args) {
		super(String.format(message, args));

	}
}
