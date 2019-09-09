package com.sept.rest.webservices.restfulwebservices.thread;

@SuppressWarnings("serial")
public class ThreadNotFoundException extends Exception {
	private long id;
	public ThreadNotFoundException(long id) {
		super(String.format("Thread with id : '%s'", id));
	}

}
