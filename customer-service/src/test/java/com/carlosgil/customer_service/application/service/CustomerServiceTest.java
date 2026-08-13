package com.carlosgil.customer_service.application.service;

import com.carlosgil.customer_service.application.port.out.CustomerRepository;
import com.carlosgil.customer_service.domain.exception.DuplicateCustomerEmailException;
import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.persistence.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void createCustomer_WithValidData_ShouldCreateCustomer(){

        //GIVEN
        when(customerRepository.save(any(Customer.class)))
                .thenReturn(Customer.builder().name("Carlos").surname("Gil").email("carlos.gil@example.com").build());
        // WHEN
        Customer result = customerService
                .createCustomer(Customer.builder().name("Carlos").surname("Gil").email("carlos.gil@example.com").build());

        // THEN (Entonces se verifica que el resultado sea el esperado)
        assertNotNull(result);
        assertEquals("Carlos", result.getName());
        assertEquals("Gil", result.getSurname());
        assertEquals("carlos.gil@example.com", result.getEmail());

        //Quiero comprobar que se ha enviado ESTE Customer
        verify(customerRepository).save(Customer.builder().name("Carlos").surname("Gil").email("carlos.gil@example.com").build());
    }

    @Test
    void createCustomer_WithDuplicatedEmail_ShouldThrowException(){

        Customer customer = new Customer();
        customer.setEmail("carlos.gil@example.com");

        //GIVEN
        when(customerRepository.existsByEmail(customer.getEmail())).thenReturn(true);

        assertThrows(
                DuplicateCustomerEmailException.class,
                () -> customerService.createCustomer(customer)
        );

        verify(customerRepository).existsByEmail(customer.getEmail());
        verify(customerRepository, never()).save(customer);

    }

    @Test
    void deleteCustomer_WithValidData_ShouldDeleteACustomer(){
        Customer customer = new Customer();
        customer.setId(2);
        // GIVEN
        when(customerRepository.deleteCustomer(customer.getId())).thenReturn(true);

        //WHEN
        boolean customerDeleted= customerService.deleteCustomer(customer.getId());
        assertTrue(customerDeleted);

        //THEN
        verify(customerRepository, times(1)).deleteCustomer(2);

    }

    @Test
    void deleteCustomer_that_does_not_exist_return_false(){
        Customer customer = new Customer();
        customer.setId(5);
        // GIVEN
        when(customerRepository.deleteCustomer(customer.getId())).thenReturn(false);

        //WHEN
        boolean customerDeleted= customerService.deleteCustomer(customer.getId());
        assertFalse(customerDeleted);

        //THEN
        verify(customerRepository, times(1)).deleteCustomer(5);
    }

    @Test
    void findCustomerById_WithValidId_ShouldReturnCustomer(){
        Customer customer = new Customer();
        customer.setId(6);
        //GIVEN
        when(customerRepository.findCustomerById(customer.getId())).thenReturn(Optional.of(customer));

        //WHEN
        Optional<Customer> customerFound = customerService.findCustomerById(customer.getId());

        assertTrue(customerFound.isPresent(), "El cliente debería estar presente");

        assertEquals(6, customerFound.get().getId());
        assertSame(customer, customerFound.get());
        //THEN
        verify(customerRepository, times(1)).findCustomerById(6);

    }

    @Test
    void updateCustomer_withValidData_shouldUpdateCustomer(){

        Customer customer = new Customer();
        customer.setId(7);
        customer.setName("Carlos");
        customer.setSurname("Gil");
        customer.setEmail("carlos.gil@example.com");

        Customer customerUpdated = new Customer();
        customerUpdated.setId(7);
        customerUpdated.setName("David");
        customerUpdated.setSurname("Alonso");
        customerUpdated.setEmail("david.alonso@example.com");

        //GIVEN
        when(customerRepository.save(customerUpdated))
                .thenReturn(customerUpdated);

        //GIVEN
        when(customerRepository.findCustomerById(customerUpdated.getId())).thenReturn(Optional.of(customer));

        // WHEN
        Customer resultUpdated = customerService
                .updateCustomer(customerUpdated);

        assertNotNull(resultUpdated);
        assertEquals("David", resultUpdated.getName());
        assertEquals("Alonso", resultUpdated.getSurname());
        assertEquals("david.alonso@example.com", resultUpdated.getEmail());


    }


}
