package com.payment.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistException extends Exception{

	public AlreadyExistException(String message) {
		super(message);
	}
}
