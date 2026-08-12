package com.carlosgil.customer_service.infrastructure.persistence.adapter.out;

import com.carlosgil.customer_service.application.port.out.CustomerRepository;
import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.persistence.entity.CustomerEntity;
import com.carlosgil.customer_service.infrastructure.persistence.repository.CustomerJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryAdapter implements CustomerRepository {

    private CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.getName());
        customerEntity.setSurname(customer.getSurname());
        customerEntity.setEmail(customer.getEmail());
        CustomerEntity savedCustomer = customerJpaRepository.save(customerEntity);
        Customer customer1 = new Customer();
        customer1.setName(savedCustomer.getName());
        customer1.setSurname(savedCustomer.getSurname());
        customer1.setEmail(savedCustomer.getEmail());
        return customer1;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }
}
