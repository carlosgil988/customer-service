package com.carlosgil.customer_service.application.service;

import com.carlosgil.customer_service.application.port.out.CustomerRepository;
import com.carlosgil.customer_service.domain.exception.CustomerNotFoundException;
import com.carlosgil.customer_service.domain.exception.DuplicateCustomerEmailException;
import com.carlosgil.customer_service.domain.model.Customer;
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
        Optional<Customer> optionalCustomerFound = customerRepository.findCustomerById(customerId);
        if(optionalCustomerFound.isEmpty()){
            throw new CustomerNotFoundException("Cliente no encontrado con id: " + customerId);
        }
        return  customerRepository.findCustomerById(customerId);
    }
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerById(customer.getId());

        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Cliente no encontrado con id: " + customer.getId());
        }

        return customerRepository.save(customer);
    }
}
