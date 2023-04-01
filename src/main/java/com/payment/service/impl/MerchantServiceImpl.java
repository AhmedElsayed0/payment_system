package com.payment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.payment.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.payment.exception.DoesNotExistException;
import com.payment.model.Merchant;
import com.payment.repository.MerchantRepository;
import com.payment.request.MerchantRequest;
import com.payment.response.MerchantResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
	
	@Autowired
	MerchantRepository merchantRepository;

	@Override
	public MerchantResponse findMerchantById(long id) throws DoesNotExistException {
		log.info("start find merchant by ID : {}", id);
		MerchantResponse merchantResponse =  new MerchantResponse();
		Merchant merchant = merchantRepository.findById(id).orElseThrow(() -> new DoesNotExistException("Merchant Does No Exist"));
		BeanUtils.copyProperties(merchant,merchantResponse);
		log.info("end find merchant by ID : {}", id);
		return merchantResponse;
	}

	@Override
	public List<MerchantResponse> findAllActiveMerchant() throws DoesNotExistException {
		log.info("start getting all Active merchant");
		List<Merchant> merchants = merchantRepository.findByIsActiveTrue();
		if (merchants.isEmpty()) {
			log.info("No any Active Merchant is available");
			throw new DoesNotExistException("Merchants Not Available");
		}
		log.info("end getting all Active merchant");
		 return merchants.stream()
		            .map(merchant -> new MerchantResponse(merchant.getId(), merchant.getName(), merchant.isActive()))
		            .collect(Collectors.toList());
	}

	@Override
	public MerchantResponse createMerchant(MerchantRequest merchantRequest) {
		log.info("start creating merchant Name : {}", merchantRequest.getName());
		Merchant merchant = new Merchant();
		BeanUtils.copyProperties(merchantRequest, merchant);
		merchant = merchantRepository.save(merchant);
		
		log.info("end creating merchant Name : {}", merchantRequest.getName());
		return MerchantResponse.builder().id(merchant.getId())
				.name(merchant.getName())
				.isActive(merchant.isActive()).build();
	}
	
	

}
