package com.leyes.app.exceptions;

import java.io.Serializable;

/**
 * 自定义异常处理
 * 
 * @author jingpeng
 *
 */
public class CustomException extends Exception  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException() {
		super( );
	}

	public CustomException(String message) {
		super(message);

	}
	public CustomException(Throwable innerException){
		super(innerException);
	}
	public CustomException(String message,Throwable innerException){
		super(message,innerException);
	}

	public CustomException(String message, Object... args) {
		super(String.format(message, args));
	}

}
