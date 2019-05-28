package com.eBay.batch.exception;
/**
 * <pre>
 * com.eBay.batch.exception_ ValidException.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class ValidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7149151216768447382L;

	public ValidException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public ValidException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public ValidException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public ValidException(Throwable cause) {
		super(cause);
	}
}
