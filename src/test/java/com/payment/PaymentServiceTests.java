package com.payment;

import com.payment.exception.DoesNotExistException;
import com.payment.model.Customer;
import com.payment.model.Merchant;
import com.payment.model.PaymentTransaction;
import com.payment.repository.CustomerRepository;
import com.payment.repository.MerchantRepository;
import com.payment.repository.PaymentTransactionRepository;
import com.payment.request.PaymentTransactionRequest;
import com.payment.response.PaymentTransactionResponse;
import com.payment.service.impl.PaymentTransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentServiceTests {
    @Mock
    private PaymentTransactionRepository paymentTransactionRepository;

    @Mock
    private MerchantRepository merchantRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private PaymentTransactionServiceImpl paymentTransactionService;

    @Test
    void testCreatePayment() throws DoesNotExistException {
        // Arrange
        PaymentTransactionRequest request = new PaymentTransactionRequest();
        request.setCustomer(1L);
        request.setMerchant(2L);
        request.setGrossAmount(100D);
        request.setReceiptId(123456L);
        request.setVatRate(0.1D);

        Customer customer = new Customer();
        customer.setId(1L);
        Mockito.when(customerRepository.findById(request.getCustomer())).thenReturn(Optional.of(customer));

        Merchant merchant = new Merchant();
        merchant.setId(2L);
        Mockito.when(merchantRepository.findById(request.getMerchant())).thenReturn(Optional.of(merchant));

        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setId(1L);
        transaction.setCustomer(customer);
        transaction.setMerchant(merchant);
        transaction.setGrossAmount(request.getGrossAmount());
        transaction.setReceiptId(request.getReceiptId());
        transaction.setVatRate(request.getVatRate());
        Mockito.when(paymentTransactionRepository.save(Mockito.any(PaymentTransaction.class))).thenReturn(transaction);

        // Act
        PaymentTransactionResponse result = paymentTransactionService.createPayment(request);

        // Assert
        assertNotNull(result);
        assertEquals(transaction.getId(), result.getId());
        assertEquals(transaction.getCustomer(), result.getCustomer());
        assertEquals(transaction.getMerchant(), result.getMerchant());
        assertEquals(transaction.getGrossAmount(), result.getGrossAmount());
        assertEquals(transaction.getReceiptId(), result.getReceiptId());
        assertEquals(transaction.getVatRate(), result.getVatRate());
    }

    @Test
    void testCreatePaymentWithInvalidCustomer() {
        // Arrange
        PaymentTransactionRequest request = new PaymentTransactionRequest();
        request.setCustomer(1L);
        request.setMerchant(2L);
        request.setGrossAmount(100D);
        request.setReceiptId(123456L);
        request.setVatRate(0.1D);

        Mockito.when(customerRepository.findById(request.getCustomer())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(DoesNotExistException.class, () -> paymentTransactionService.createPayment(request));
    }

    @Test
    void testCreatePaymentWithInvalidMerchant() {
        // Arrange
        PaymentTransactionRequest request = new PaymentTransactionRequest();
        request.setCustomer(1L);
        request.setMerchant(2L);
        request.setGrossAmount(100D);
        request.setReceiptId(123456L);
        request.setVatRate(0.1D);

        Customer customer = new Customer();
        customer.setId(1L);
        Mockito.when(customerRepository.findById(request.getCustomer())).thenReturn(Optional.of(customer));

        Mockito.when(merchantRepository.findById(request.getMerchant())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(DoesNotExistException.class, () -> paymentTransactionService.createPayment(request));
    }
}
