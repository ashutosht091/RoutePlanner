package com.goeuro.RoutePlanner.pojo;

public class ValidationException extends Exception {

	/**
	 * class used to throw validation exception
	 */
	private static final long serialVersionUID = 1L;
	String errorString;	

	public ValidationException(String errorString)
	{
		super(errorString);
		this.errorString = errorString;
	
	}

	@Override
	public String toString() {
		return "ValidationException [errorString=" + errorString + "]";
	}


}
