package com.practice.spboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExceptions extends RuntimeException {
	private FieldError error;

	public UserExceptions(FieldError error) {
		this.error = error;
	}
	
	public UserExceptions(String obj, String field, String msg) {
		this.error = new FieldError(obj, field, msg);
	}
	
	public FieldError getError() {
		return error;
	}
}
