package com.payment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.exception.DoesNotExistException;
import com.payment.request.MerchantRequest;
import com.payment.response.MerchantResponse;
import com.payment.service.MerchantService;

@RestController
@RequestMapping(value = MerchantController.API_V_1_MERCHANTS)
public class MerchantController {

	public static final String API_V_1_MERCHANTS = "api/v1/merchants";
	@Autowired
	MerchantService merchantService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MerchantResponse> getMerchantById(@PathVariable long id) throws DoesNotExistException{
		MerchantResponse responseEntity = merchantService.findMerchantById(id);
		return ResponseEntity.ok().body(responseEntity);
		
	}
	
	@GetMapping("")
	public ResponseEntity<List<MerchantResponse>> getAllActiveMerchants() throws DoesNotExistException{
		List<MerchantResponse> responseEntity = merchantService.findAllActiveMerchant();
		return ResponseEntity.ok().body(responseEntity);
	}
	
	@PostMapping("")
	public ResponseEntity<MerchantResponse> createMerchant(@RequestBody MerchantRequest merchantRequest){
		MerchantResponse merchantResponse = merchantService.createMerchant(merchantRequest);
		 return ResponseEntity.created(URI.create("/merchants/" + merchantResponse.getId())).body(merchantResponse);
	}

}
