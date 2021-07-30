package com.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidParameterException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OutOfStockException extends Exception
{

	private static final long serialVersionUID = 1L;

	public OutOfStockException(String message) {
		super(message);
	}

}
