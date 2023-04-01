package com.payment.service;

import com.payment.exception.DoesNotExistException;
import com.payment.request.PaymentTransactionRequest;
import com.payment.response.PaymentTransactionResponse;

public interface PaymentTransactionService {
	
	PaymentTransactionResponse createPayment(PaymentTransactionRequest request)throws DoesNotExistException;

}
