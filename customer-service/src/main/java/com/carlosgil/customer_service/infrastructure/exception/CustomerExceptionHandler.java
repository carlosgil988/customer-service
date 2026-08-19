package com.carlosgil.customer_service.infrastructure.exception;

import com.carlosgil.customer.api.model.CustomerErrorDuplicateEmail;
import com.carlosgil.customer.api.model.CustomerErrorNotFound;
import com.carlosgil.customer_service.domain.exception.CustomerNotFoundException;
import com.carlosgil.customer_service.domain.exception.DuplicateCustomerEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<CustomerErrorNotFound> handleResourceNotFound(CustomerNotFoundException ex) {
        CustomerErrorNotFound customerErrorNotFound = new CustomerErrorNotFound();
        customerErrorNotFound.setCode("CUSTOMER_DOES_NOT_EXIST");
        customerErrorNotFound.setMessage(ex.getMessage());
        return new ResponseEntity<>(customerErrorNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateCustomerEmailException.class)
    public ResponseEntity<CustomerErrorDuplicateEmail> handleResourceDuplicatedEmail(DuplicateCustomerEmailException ex) {
        CustomerErrorDuplicateEmail customerErrorDuplicateEmail = new CustomerErrorDuplicateEmail();
        customerErrorDuplicateEmail.setCode("CUSTOMER_ERROR_DUPLICATE_EMAIL");
        customerErrorDuplicateEmail.setMessage(ex.getMessage());
        return new ResponseEntity<>(customerErrorDuplicateEmail, HttpStatus.CONFLICT);
    }
}