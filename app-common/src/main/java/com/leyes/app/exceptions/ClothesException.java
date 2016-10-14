package com.leyes.app.exceptions;



public class ClothesException extends CustomException {

	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1000001L;

	public ClothesException() {
		super();
	}

	public ClothesException(String message) {
		super(message);

	}
	public ClothesException(Throwable innerException){
		super(innerException);
	}
	public ClothesException(String message,Throwable innerException){
		super(message,innerException);
	}

	public ClothesException(String message, Object... args) {
		super(String.format(message, args));

	}
}
