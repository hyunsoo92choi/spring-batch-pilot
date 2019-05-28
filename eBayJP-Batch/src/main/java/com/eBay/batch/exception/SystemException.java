package com.eBay.batch.exception;
/**
 * <pre>
 * com.eBay.batch.exception_ SystemException.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5296411640406967403L;
	
	public SystemException() {
		super();
	}
	
	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public SystemException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}
}
