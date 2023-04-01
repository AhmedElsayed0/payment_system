package com.payment.service;

import java.util.List;

import com.payment.exception.DoesNotExistException;
import com.payment.model.Customer;
import com.payment.request.CustomerRequest;
import com.payment.response.CustomerResponse;

public interface CustomerService {

	List<Customer> findAllCustomers();

	CustomerResponse createCustomer(CustomerRequest customerRequest);

	List<CustomerResponse> getAllCustomers()throws DoesNotExistException;
}
