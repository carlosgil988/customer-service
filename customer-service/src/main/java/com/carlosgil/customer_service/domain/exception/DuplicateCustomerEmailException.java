package com.carlosgil.customer_service.domain.exception;

public class DuplicateCustomerEmailException extends RuntimeException {
    public DuplicateCustomerEmailException(String message) {
        super(message);
    }
}
