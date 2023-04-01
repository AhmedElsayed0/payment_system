package com.payment.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleException(Exception exception) {
	  Map<Class<? extends Exception>, HttpStatus> exceptionMappings = new HashMap<>();
	  exceptionMappings.put(AlreadyExistException.class, HttpStatus.UNAUTHORIZED);
	  exceptionMappings.put(DoesNotExistException.class, HttpStatus.NOT_FOUND);
	  // add more mappings for other exceptions here if needed

	  HttpStatus httpStatus = exceptionMappings.getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
	  return ResponseEntity.status(httpStatus)
	      .body(ProblemDetail.forStatusAndDetail(httpStatus.value(), exception.getMessage()));
	}

}
