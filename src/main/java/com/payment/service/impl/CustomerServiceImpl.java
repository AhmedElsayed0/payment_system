package com.payment.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.payment.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.exception.DoesNotExistException;
import com.payment.model.Customer;
import com.payment.repository.CustomerRepository;
import com.payment.request.CustomerRequest;
import com.payment.response.CustomerResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> findAllCustomers() {
		
		return customerRepository.findAll();
	}

	@Override
	public CustomerResponse createCustomer(CustomerRequest customerRequest) {
		log.info("start creating customer Name : {}", customerRequest.getName());
		Customer customer = new Customer();
		customer.setDateOfRegistration(LocalDate.now());
		BeanUtils.copyProperties(customerRequest, customer);
		customerRepository.save(customer);
		return CustomerResponse.builder()
				.id(customer.getId())
				.name(customer.getName())
				.email(customer.getEmail())
				.dateOfRegistration(customer.getDateOfRegistration()).build();
	}

	@Override
	public List<CustomerResponse> getAllCustomers() throws DoesNotExistException {
		log.info("start getting All customers");
		List<Customer> customers = customerRepository.findAll();
		if (customers.isEmpty()) {
			log.info("No any customer available");
			throw new DoesNotExistException("Customers Not Available");
		}
		log.info("end getting All customers");
		 return customers.stream()
		            .map(customer -> new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail(),
		            	 customer.getDateOfRegistration()))
		            .collect(Collectors.toList());
	}

}
