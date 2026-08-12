package com.carlosgil.customer_service.application.service;

import com.carlosgil.customer_service.application.port.out.CustomerRepository;
import com.carlosgil.customer_service.domain.exception.DuplicateCustomerEmailException;
import com.carlosgil.customer_service.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void createCustomer_WithValidData_ShouldCreateCustomer(){


        when(customerRepository.save(any(Customer.class)))
                .thenReturn(Customer.builder().name("Carlos").surname("Gil").email("carlos.gil@example.com").build());
        // WHEN
        Customer result = customerService
                .createCustomer(Customer.builder().name("Carlos").surname("Gil").email("carlos.gil@example.com").build());

        // THEN (Entonces se verifica que el resultado sea el esperado)
        assertNotNull(result);
        assertEquals("Carlos", result.getName());
        assertEquals("Gil", result.getSurname());
        assertEquals("carlos.gil@example.com", result.getEmail());

        //Quiero comprobar que se ha enviado ESTE Customer
        verify(customerRepository).save(Customer.builder().name("Carlos").surname("Gil").email("carlos.gil@example.com").build());
    }

    @Test
    void createCustomer_WithDuplicatedEmail_ShouldThrowException(){

        Customer customer = new Customer();
        customer.setEmail("carlos.gil@example.com");

        //GIVEN
        when(customerRepository.existsByEmail(customer.getEmail())).thenReturn(true);

        assertThrows(
                DuplicateCustomerEmailException.class,
                () -> customerService.createCustomer(customer)
        );

        verify(customerRepository).existsByEmail(customer.getEmail());
        verify(customerRepository, never()).save(customer);

    }


}
