package com.webonise.custom.exceptions;

public class DataLimitException extends Exception{
	
	 private String message = null;
	 
	    public DataLimitException() {
	        super();
	    }
	 
	    public DataLimitException(String message) {
	        super(message);
	        this.message = message;
	    }
	 
	    public DataLimitException(Throwable cause) {
	        super(cause);
	    }
	 
	    @Override
	    public String toString() {
	        return message;
	    }
	 
	    @Override
	    public String getMessage() {
	        return message;
	    }
}
