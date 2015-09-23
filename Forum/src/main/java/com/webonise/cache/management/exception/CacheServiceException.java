package com.webonise.cache.management.exception;

public class CacheServiceException extends  RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CacheServiceException(String message) {
		super(message);
	}

	public CacheServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public CacheServiceException(Throwable throwable) {
		super(throwable);
	}
}