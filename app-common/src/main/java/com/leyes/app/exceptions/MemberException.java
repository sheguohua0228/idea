package com.leyes.app.exceptions;



public class MemberException  extends CustomException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemberException() {
		super( );
	}

	public MemberException(String message) {
		super(message);

	}
	public MemberException(Throwable innerException){
		super(innerException);
	}
	public MemberException(String message,Throwable innerException){
		super(message,innerException);
	}

	public MemberException(String message, Object... args) {
		super(String.format(message, args));
	}
}
