package com.carlosgil.customer_service.mapper;

import com.carlosgil.customer.api.model.Customer;
import com.carlosgil.customer_service.infrastructure.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerMapperTest {

    private final CustomerMapper customerMapper = new CustomerMapper();

    @Test
    void shouldMapCustomerModelToCustomerApi() {
        com.carlosgil.customer_service.domain.model.Customer customer =
                new com.carlosgil.customer_service.domain.model.Customer();
        customer.setId(3);
        customer.setName("Carlos");
        customer.setSurname("Gil");
        customer.setEmail("carlosgilmunoz87@gmail.com");

        // WHEN
        Customer customerMapperResult =  customerMapper.mapToApi(customer);

        assertNotNull(customerMapper);
        assertEquals(3, customerMapperResult.getId());
        assertEquals("Carlos", customerMapperResult.getName());
        assertEquals("Gil", customerMapperResult.getSurname());
        assertEquals("carlosgilmunoz87@gmail.com", customerMapperResult.getEmail());

    }
}
