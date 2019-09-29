package com.sept.rest.webservices.restfulwebservices.exception;

@SuppressWarnings("serial")
public class ExistingUserException extends Exception{
	public ExistingUserException() {
		super(String.format("User already exists"));
	}
}
