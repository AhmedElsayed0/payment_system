package com.payment.request;

import java.time.LocalDate;

import com.payment.model.Customer;
import com.payment.model.Merchant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTransactionRequest {
	
	private double grossAmount;
	private double vatRate;
	private long receiptId;
    private long customer;
    private long merchant;

}
