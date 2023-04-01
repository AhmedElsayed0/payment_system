package com.payment.service.impl;

import java.time.LocalDate;

import com.payment.service.PaymentTransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.exception.DoesNotExistException;
import com.payment.model.PaymentTransaction;
import com.payment.repository.CustomerRepository;
import com.payment.repository.MerchantRepository;
import com.payment.repository.PaymentTransactionRepository;
import com.payment.request.PaymentTransactionRequest;
import com.payment.response.PaymentTransactionResponse;

@Service
public class PaymentTransactionServiceImpl implements PaymentTransactionService {
	
	@Autowired
	PaymentTransactionRepository paymentTransactionRepository;
	
	@Autowired
	MerchantRepository merchantRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public PaymentTransactionResponse createPayment(PaymentTransactionRequest request) throws DoesNotExistException {
		
		PaymentTransaction transaction = new PaymentTransaction();
	    transaction.setTransactionDate(LocalDate.now());
	    transaction.setCustomer(customerRepository.findById(request.getCustomer())
	            .orElseThrow(() -> new DoesNotExistException("Invalid Customer")));
	    transaction.setMerchant(merchantRepository.findById(request.getMerchant())
	            .orElseThrow(() -> new DoesNotExistException("Invalid Merchant")));
	    transaction.setGrossAmount(request.getGrossAmount());
	    transaction.setReceiptId(request.getReceiptId());
	    transaction.setVatRate(request.getVatRate());
		
		transaction = paymentTransactionRepository.save(transaction);
		return PaymentTransactionResponse.builder()
				.customer(transaction.getCustomer())
				.merchant(transaction.getMerchant())
				.grossAmount(transaction.getGrossAmount())
				.id(transaction.getId())
				.receiptId(transaction.getReceiptId())
				.vatRate(transaction.getVatRate()).build();
	}

}
