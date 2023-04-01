package com.payment.service;

import java.util.List;
import java.util.Optional;

import com.payment.exception.DoesNotExistException;
import com.payment.model.Merchant;
import com.payment.request.MerchantRequest;
import com.payment.response.MerchantResponse;

public interface MerchantService {

	MerchantResponse findMerchantById(long id)throws DoesNotExistException;
	List<MerchantResponse> findAllActiveMerchant() throws DoesNotExistException;
	MerchantResponse createMerchant(MerchantRequest merchantRequest);
}
