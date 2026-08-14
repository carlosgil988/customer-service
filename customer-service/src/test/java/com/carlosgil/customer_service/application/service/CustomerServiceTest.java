package com.carlosgil.customer_service.application.service;

import com.carlosgil.customer_service.application.port.out.CustomerRepository;
import com.carlosgil.customer_service.domain.exception.CustomerNotFoundException;
import com.carlosgil.customer_service.domain.exception.DuplicateCustomerEmailException;
import com.carlosgil.customer_service.domain.model.Customer;
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
        //GIVEN
        when(customerRepository.findCustomerById(customer.getId())).thenReturn(customer);

        //WHEN
        customerService.deleteCustomer(customer.getId());

        //THEN
        verify(customerRepository, times(1)).deleteById(2);

    }

    @Test
    void deleteCustomer_WhenCustomerDoesNotExist_ShouldThrowCustomerNotFoundException(){
        Customer customer = new Customer();
        customer.setId(99);

        //GIVEN
        when(customerRepository.findCustomerById(customer.getId())).thenReturn(null);

        //WHEN
        assertThrows(
                CustomerNotFoundException.class,
                () -> customerService.deleteCustomer(customer.getId())
        );

    }

    @Test
    void findCustomerById_WithValidId_ShouldReturnCustomer(){
        Customer customer = new Customer();
        customer.setId(6);
        //GIVEN
        when(customerRepository.findCustomerById(customer.getId())).thenReturn(customer);

        //WHEN
        Customer customerFound = customerService.findCustomerById(customer.getId());

        assertNotNull(customerFound);

        assertEquals(6, customerFound.getId());
        assertSame(customer, customerFound);
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
        when(customerRepository.findCustomerById(customerUpdated.getId())).thenReturn(customer);

        // WHEN
        Customer resultUpdated = customerService
                .updateCustomer(customerUpdated);

        assertNotNull(resultUpdated);
        assertEquals("David", resultUpdated.getName());
        assertEquals("Alonso", resultUpdated.getSurname());
        assertEquals("david.alonso@example.com", resultUpdated.getEmail());


    }



}
