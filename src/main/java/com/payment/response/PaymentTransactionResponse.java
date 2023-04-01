package com.payment.response;

import java.time.LocalDate;

import com.payment.model.Customer;
import com.payment.model.Merchant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionResponse {

	private long id;
    private  LocalDate transactionDate;
	private double grossAmount;
	private double vatRate;
	private long receiptId;
    private Customer customer;
    private Merchant merchant;
}
