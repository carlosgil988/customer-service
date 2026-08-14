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

    public void deleteCustomer(int customerId){
        Customer customerFound = findCustomerById(customerId);
        if(customerFound!=null){
            customerRepository.deleteById(customerId);
        }
    }

    public Customer findCustomerById(int customerId) {
        Customer customerFound = customerRepository.findCustomerById(customerId);
        if(customerFound== null){
            throw new CustomerNotFoundException("Cliente no encontrado con id: " + customerId);
        }
        return  customerFound;
    }
    public Customer updateCustomer(Customer customer) {
        Customer updateCustomer = customerRepository.findCustomerById(customer.getId());

        if (updateCustomer == null) {
            throw new CustomerNotFoundException("Cliente no encontrado con id: " + customer.getId());
        }

        return customerRepository.save(customer);
    }
}
