package com.carlosgil.customer_service.domain.exception;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerNotFoundException  extends RuntimeException {
    private String id;
}
