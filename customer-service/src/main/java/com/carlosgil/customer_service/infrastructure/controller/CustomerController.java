package com.carlosgil.customer_service.infrastructure.controller;

import com.carlosgil.customer.api.CustomerApi;
import com.carlosgil.customer.api.model.Customer;
import com.carlosgil.customer_service.application.service.CustomerService;
import com.carlosgil.customer_service.infrastructure.mapper.CustomerMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController  implements CustomerApi {

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
    @Override
    public ResponseEntity<Void> deleteCustomerById(@NotNull Integer id) {
        customerService.deleteCustomer(id);
        return   ResponseEntity.noContent().build();
    }

}
