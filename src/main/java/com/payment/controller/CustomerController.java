package com.payment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.exception.DoesNotExistException;
import com.payment.request.CustomerRequest;
import com.payment.response.CustomerResponse;
import com.payment.service.CustomerService;

@RestController
@RequestMapping(value = CustomerController.API_V_1_CUSTOMERS)
public class CustomerController {

	public static final String API_V_1_CUSTOMERS = "api/v1/customers";
	@Autowired
	CustomerService customerService;
	
	@PostMapping("")
	public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
		CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
		return ResponseEntity.created(URI.create("/customers/" + customerResponse.getId()))
				.body(customerResponse);
	}
	
	@GetMapping("")
	public ResponseEntity<List<CustomerResponse>> getAllCustomers() throws DoesNotExistException{
		
		List<CustomerResponse> customerList = customerService.getAllCustomers();
		return ResponseEntity.ok().body(customerList);
	}

}
