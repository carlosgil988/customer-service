package com.carlosgil.customer_service.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void createCustomer_WithValidData_ShouldCreateCustomer(){
        // GIVEN mejor usar pattern builder

        Customer customer = new Customer();
        customer.name("Carlos");
        customer.surName("Gil");
        customer.email("carlos.gil@example.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        // WHEN
        Customer result = customerService.createCustomer(customer);



        // THEN (Entonces se verifica que el resultado sea el esperado)
        assertNotNull(result);
    }
}
