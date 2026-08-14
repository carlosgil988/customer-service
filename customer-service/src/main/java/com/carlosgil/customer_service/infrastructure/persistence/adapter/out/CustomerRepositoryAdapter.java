package com.carlosgil.customer_service.infrastructure.persistence.adapter.out;

import com.carlosgil.customer_service.application.port.out.CustomerRepository;
import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.persistence.entity.CustomerEntity;
import com.carlosgil.customer_service.infrastructure.persistence.repository.CustomerJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity savedCustomer = customerJpaRepository.save(CustomerEntity.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail()).build());

      return  Customer.builder()
              .id(savedCustomer.getId())
              .name(savedCustomer.getName())
              .surname(savedCustomer.getSurname())
              .email(savedCustomer.getEmail()).build();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }

    @Override
    public Boolean deleteCustomer(int id) {
        return customerJpaRepository.deleteById(id);
    }

    @Override
    public Customer findCustomerById(int id) {

        CustomerEntity customerById = customerJpaRepository.findCustomerById(id);
        return Customer.builder()
                .id(customerById.getId())
                .name(customerById.getName())
                .surname(customerById.getSurname())
                .email(customerById.getEmail()).build();

    }

}
