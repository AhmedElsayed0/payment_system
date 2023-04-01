package com.payment.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.exception.DoesNotExistException;
import com.payment.request.PaymentTransactionRequest;
import com.payment.response.PaymentTransactionResponse;
import com.payment.service.PaymentTransactionService;

@RestController
@RequestMapping(value = "api/v1/payments")
public class PaymentTransactionController {
	
	@Autowired
	PaymentTransactionService paymentTransactionService;
	
	@PostMapping("")
	public ResponseEntity<PaymentTransactionResponse> createPayment(@RequestBody PaymentTransactionRequest request) throws DoesNotExistException{
		PaymentTransactionResponse paymentTransactionResponse =paymentTransactionService.createPayment(request);
		return ResponseEntity.created(URI.create("/merchants/" + paymentTransactionResponse.getId())).body(paymentTransactionResponse);
	}

}
