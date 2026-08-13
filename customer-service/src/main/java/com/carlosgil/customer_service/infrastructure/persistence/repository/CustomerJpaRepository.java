package com.carlosgil.customer_service.infrastructure.persistence.repository;

import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {

     boolean existsByEmail(String email);
     boolean deleteCustomer(int id);
     Optional<CustomerEntity> findCustomerById(int id);

}
