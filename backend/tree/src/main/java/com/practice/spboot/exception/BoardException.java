package com.practice.spboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BoardException extends RuntimeException {
	private FieldError error;

	public BoardException(FieldError error) {
		this.error = error;
	}
	
	public BoardException(String obj, String field, String msg) {
		this.error = new FieldError(obj, field, msg);
	}
	
	public FieldError getError() {
		return error;
	}
}
