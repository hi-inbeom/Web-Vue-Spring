package com.practice.spboot.exception;

import org.springframework.http.HttpStatus;

public class UserExceptions extends RuntimeException {
	private HttpStatus status;
	public HttpStatus getStatus() {
		return status;
	}
	
	public UserExceptions(String message) {
		super(message);
		this.status = HttpStatus.BAD_REQUEST;
	}
}
