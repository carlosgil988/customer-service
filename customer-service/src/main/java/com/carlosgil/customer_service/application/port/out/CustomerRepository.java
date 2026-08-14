package com.carlosgil.customer_service.application.port.out;

import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.persistence.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Boolean existsByEmail(String email);
    Boolean deleteCustomer(int id);
    Customer findCustomerById(int id);
}
