package com.carlosgil.customer_service.application.service;

import com.carlosgil.customer_service.application.port.out.CustomerRepository;
import com.carlosgil.customer_service.domain.exception.DuplicateCustomerEmailException;
import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.persistence.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new DuplicateCustomerEmailException();
        }
            return customerRepository.save(customer);
    }

    public boolean deleteCustomer(int customerId){
        return customerRepository.deleteCustomer(customerId);
    }

    public Optional<Customer> findCustomerById(int customerId) {
        return customerRepository.findCustomerById(customerId);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer);
    }
}
