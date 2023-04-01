package com.payment.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProblemDetail {

	private int status;
	private String message;
	private String error;
	public static ProblemDetail forStatusAndDetail(int value, String message2) {
		return ProblemDetail.builder()
				.message(message2)
				.status(value).build();
	}
}
