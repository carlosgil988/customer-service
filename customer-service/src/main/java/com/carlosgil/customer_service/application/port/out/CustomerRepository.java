package com.carlosgil.customer_service.application.port.out;

import com.carlosgil.customer_service.domain.model.Customer;

public interface CustomerRepository {
    Customer save(Customer customer);
    Boolean existsByEmail(String email);
    void deleteById(int id);
    Customer findCustomerById(int id);
}
