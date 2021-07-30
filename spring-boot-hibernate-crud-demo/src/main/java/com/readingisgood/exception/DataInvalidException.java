package com.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidParameterException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataInvalidException extends InvalidParameterException {

	private static final long serialVersionUID = 1L;

	public DataInvalidException(String message) {
		super(message);
	}

}
