package com.payment;

import com.payment.exception.DoesNotExistException;
import com.payment.model.Merchant;
import com.payment.repository.MerchantRepository;
import com.payment.request.MerchantRequest;
import com.payment.response.MerchantResponse;
import com.payment.service.impl.MerchantServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MerchantServiceTests {

    @Mock
    private MerchantRepository merchantRepository;

    @InjectMocks
    private MerchantServiceImpl merchantService;

    @Test
    void testFindMerchantById() throws DoesNotExistException {
        // Arrange
        long id = 1;
        Merchant merchant = new Merchant();
        merchant.setId(id);
        merchant.setName("Test Merchant");
        Mockito.when(merchantRepository.findById(id)).thenReturn(Optional.of(merchant));

        // Act
        MerchantResponse result = merchantService.findMerchantById(id);

        // Assert
        assertEquals(id, result.getId());
        assertEquals(merchant.getName(), result.getName());
    }

    @Test
    void testFindAllActiveMerchant() throws DoesNotExistException {
        // Arrange
        List<Merchant> merchants = new ArrayList<>();
        merchants.add(new Merchant(1, "Merchant 1", true));
        merchants.add(new Merchant(2, "Merchant 2", true));
        Mockito.when(merchantRepository.findByIsActiveTrue()).thenReturn(merchants);

        // Act
        List<MerchantResponse> result = merchantService.findAllActiveMerchant();

        // Assert
        assertEquals(merchants.size(), result.size());
        assertEquals(merchants.get(0).getName(), result.get(0).getName());
        assertEquals(merchants.get(1).getName(), result.get(1).getName());
    }

    @Test
    void testCreateMerchant() {
        // Arrange
        MerchantRequest merchantRequest = new MerchantRequest();
        merchantRequest.setName("Test Merchant");
        merchantRequest.setActive(Boolean.TRUE);
        Mockito.when(merchantRepository.save(Mockito.any(Merchant.class))).thenAnswer((InvocationOnMock invocation) -> {
            Merchant merchant = invocation.getArgument(0);
            merchant.setId(1L);
            return merchant;
        });

        // Act
        MerchantResponse result = merchantService.createMerchant(merchantRequest);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals(merchantRequest.getName(), result.getName());
        assertTrue(result.isActive());
    }
}
