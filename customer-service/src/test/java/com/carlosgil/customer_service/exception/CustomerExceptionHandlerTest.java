package com.carlosgil.customer_service.exception;

import com.carlosgil.customer.api.model.CustomerErrorDuplicateEmail;
import com.carlosgil.customer.api.model.CustomerErrorNotFound;
import com.carlosgil.customer_service.domain.exception.CustomerNotFoundException;
import com.carlosgil.customer_service.domain.exception.DuplicateCustomerEmailException;
import com.carlosgil.customer_service.infrastructure.exception.CustomerExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerExceptionHandlerTest {

    @InjectMocks
    private CustomerExceptionHandler customerExceptionHandler;

    @Test
    void given_a_customer_does_not_exists_we_should_handle_the_exception() {
        CustomerNotFoundException customerErrorNotFound = new CustomerNotFoundException("customer does not exists");
        ResponseEntity<CustomerErrorNotFound> customerErrorNotFoundResponseEntity =
                customerExceptionHandler.handleResourceNotFound(customerErrorNotFound);
        assertNotNull(customerErrorNotFoundResponseEntity.getBody());
        assertEquals("customer does not exists", customerErrorNotFoundResponseEntity.getBody().getMessage());
        assertEquals("CUSTOMER_DOES_NOT_EXIST", customerErrorNotFoundResponseEntity.getBody().getCode());
        assertEquals(HttpStatus.NOT_FOUND, customerErrorNotFoundResponseEntity.getStatusCode());
    }
    @Test
    void given_a_customer_whose_email_exists_we_should_handle_the_exception() {
        DuplicateCustomerEmailException duplicateCustomerEmailException = new DuplicateCustomerEmailException("customer has duplicated email");
        ResponseEntity<CustomerErrorDuplicateEmail> customerDuplicatedResponseEntity =
                customerExceptionHandler.handleResourceDuplicatedEmail(duplicateCustomerEmailException);
        assertNotNull(customerDuplicatedResponseEntity.getBody());
        assertEquals("customer has duplicated email", customerDuplicatedResponseEntity.getBody().getMessage());
        assertEquals("CUSTOMER_ERROR_DUPLICATE_EMAIL", customerDuplicatedResponseEntity.getBody().getCode());
        assertEquals(HttpStatus.CONFLICT, customerDuplicatedResponseEntity.getStatusCode());


    }

}
