package com.carlosgil.customer_service.infrastructure.persistence.repository;

import com.carlosgil.customer_service.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {

     boolean existsByEmail(String email);

}
