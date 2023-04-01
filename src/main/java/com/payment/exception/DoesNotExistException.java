package com.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DoesNotExistException extends Exception{

	public DoesNotExistException(String error) {
		super(error);
	}
}
