package com.leyes.app.exceptions;



public class MessageException  extends CustomException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageException() {
		super( );
	}

	public MessageException(String message) {
		super(message);

	}
	public MessageException(Throwable innerException){
		super(innerException);
	}
	public MessageException(String message,Throwable innerException){
		super(message,innerException);
	}

	public MessageException(String message, Object... args) {
		super(String.format(message, args));
	}
}
