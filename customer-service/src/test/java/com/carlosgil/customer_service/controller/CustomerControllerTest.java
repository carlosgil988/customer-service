package com.carlosgil.customer_service.controller;

import com.carlosgil.customer_service.application.service.CustomerService;
import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;


    @Test
    void given_a_customer_with_a_valid_id_then_we_should_return_a_customer_with_valid_data() {

        // GIVEN
        Customer customer = Customer.builder()
                .id(6)
                .name("Carlos")
                .surname("Gil")
                .email("carlos.gil@example.com")
                .build();

        when(customerService.findCustomerById(6))
                .thenReturn(customer);

        // WHEN
        ResponseEntity<com.carlosgil.customer.api.model.Customer> response =
                customerController.getCustomerById(6);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals((int) response.getBody().getId(), customer.getId());
        assertEquals(response.getBody().getSurname(), customer.getSurname());
        assertEquals(response.getBody().getName(), customer.getName());
        assertEquals(response.getBody().getEmail(), customer.getEmail());

    }
}
