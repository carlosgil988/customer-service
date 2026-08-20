package com.carlosgil.customer_service.integration;

import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.persistence.adapter.out.CustomerRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CustomerRepositoryAdapterIT {


    @Autowired
    private CustomerRepositoryAdapter customerRepositoryAdapter;



    @Test
    void shouldCreateCustomerAndPersistInDatabase(){
        //save first
        Customer savedCustomer = customerRepositoryAdapter
                .save(Customer.builder().name("Carlos").surname("Gil").email("carlos.gil@example.com").build());

        assertNotNull(savedCustomer);
        assertEquals("Carlos", savedCustomer.getName());
        assertEquals("Gil", savedCustomer.getSurname());
        assertEquals("carlos.gil@example.com", savedCustomer.getEmail());

        //check that has been inserted
        Boolean existsCustomer = customerRepositoryAdapter.existsByEmail("carlos.gil@example.com");
        assertTrue(existsCustomer);
    }
    @Test
    void shouldDeleteCustomerAndPersistInDatabase() {
        Customer customer = new Customer();
        customer.setId(5);
        customerRepositoryAdapter.deleteById(customer.getId());
    }
    @Test
    void should_find_customer_by_id_and_retrieve_data_from_database() {
        Customer customer = new Customer();
        customer.setId(2);
        Customer customerById = customerRepositoryAdapter.findCustomerById(customer.getId());
        assertNotNull(customerById);
        assertEquals("Ana", customerById.getName());
        assertEquals("López", customerById.getSurname());
        assertEquals("ana.lopez@example.com", customerById.getEmail());

    }

    @Test
    void should_find_customer_by_id_and_email_and_retrieve_data_from_database() {
        Customer customer = new Customer();
        customer.setEmail("ana.lopez@example.com");
        customer.setId(7);
        Boolean foundCustomer = customerRepositoryAdapter.existsByEmailAndIdNot(customer.getEmail(), customer.getId());
        assertTrue(foundCustomer);
    }





}
