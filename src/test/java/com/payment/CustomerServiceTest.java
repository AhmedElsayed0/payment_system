package com.payment;

import com.payment.exception.DoesNotExistException;
import com.payment.model.Customer;
import com.payment.repository.CustomerRepository;
import com.payment.request.CustomerRequest;
import com.payment.response.CustomerResponse;
import com.payment.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testFindAllCustomers() {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "username", "sample.doe@example.com", LocalDate.of(2022, 1, 1)));
        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<Customer> result = customerService.findAllCustomers();

        // Assert
        assertEquals(customers.size(), result.size());
        assertEquals(customers.get(0).getName(), result.get(0).getName());
        assertEquals(customers.get(0).getEmail(), result.get(0).getEmail());
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("username");
        customerRequest.setEmail("sample.doe@example.com");
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenAnswer((InvocationOnMock invocation) -> {
            Customer customer = invocation.getArgument(0);
            customer.setId(1L);
            return customer;
        });

        // Act
        CustomerResponse result = customerService.createCustomer(customerRequest);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals(customerRequest.getName(), result.getName());
        assertEquals(customerRequest.getEmail(), result.getEmail());
        assertNotNull(result.getDateOfRegistration());
    }

    @Test
    void testGetAllCustomers() throws DoesNotExistException {
        // Arrange
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "username", "sample.doe@example.com", LocalDate.of(2022, 1, 1)));
        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<CustomerResponse> result = customerService.getAllCustomers();

        // Assert
        assertEquals(customers.size(), result.size());
        assertEquals(customers.get(0).getName(), result.get(0).getName());
        assertEquals(customers.get(0).getEmail(), result.get(0).getEmail());
    }
}
