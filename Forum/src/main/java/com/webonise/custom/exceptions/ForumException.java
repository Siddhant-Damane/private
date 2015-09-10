package com.webonise.custom.exceptions;

public class ForumException extends Exception{
	
	 private String message = null;
	 
	    public ForumException() {
	        super();
	    }
	 
	    public ForumException(String message) {
	        super(message);
	        this.message = message;
	    }
	 
	    public ForumException(Throwable cause) {
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
