package com.carlosgil.customer_service.infrastructure.controller;

import com.carlosgil.customer.api.CustomerApi;
import com.carlosgil.customer.api.model.Customer;
import com.carlosgil.customer.api.model.CustomerCreateRequest;
import com.carlosgil.customer_service.application.service.CustomerService;
import com.carlosgil.customer_service.infrastructure.mapper.CustomerMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CustomerController  implements CustomerApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<Customer> createCustomer(@Valid CustomerCreateRequest customerCreateRequest) {
        com.carlosgil.customer_service.domain.model.Customer customer
                = new com.carlosgil.customer_service.domain.model.Customer();
        customer.setName(customerCreateRequest.getName());
        customer.setSurname(customerCreateRequest.getSurname());
        customer.setEmail(customerCreateRequest.getEmail());
        com.carlosgil.customer_service.domain.model.Customer customerCreated = customerService.createCustomer(customer);
        CustomerMapper mappedCustomer = new CustomerMapper();
        Customer customerMapped = mappedCustomer.mapToApi(customerCreated);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerMapped.getId())
                .toUri();

        return ResponseEntity.created(location).body(customerMapped);
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
