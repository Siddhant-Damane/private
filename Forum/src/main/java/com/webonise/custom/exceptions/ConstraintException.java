package com.webonise.custom.exceptions;

public class ConstraintException extends Exception {
	 private String message = null;
	 
	    public ConstraintException() {
	        super();
	    }
	 
	    public ConstraintException(String message) {
	        super(message);
	        this.message = message;
	    }
	 
	    public ConstraintException(Throwable cause) {
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
