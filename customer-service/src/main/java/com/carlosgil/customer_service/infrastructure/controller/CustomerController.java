package com.carlosgil.customer_service.infrastructure.controller;

import com.carlosgil.customer.api.CustomersApi;
import com.carlosgil.customer.api.model.Customer;
import com.carlosgil.customer_service.application.service.CustomerService;
import com.carlosgil.customer_service.infrastructure.mapper.CustomerMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

public class CustomerController  implements CustomersApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(@NotNull Integer id) {
        com.carlosgil.customer_service.domain.model.Customer
                customerById = customerService.findCustomerById(id);
        CustomerMapper mappedCustomer = new CustomerMapper();
        Customer customer = mappedCustomer.mapToApi(customerById);
        return ResponseEntity.ok(customer);
    }
}
