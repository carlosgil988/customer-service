package com.carlosgil.customer_service.controller;

import com.carlosgil.customer.api.model.CustomerUpdateRequest;
import com.carlosgil.customer_service.application.service.CustomerService;
import com.carlosgil.customer_service.domain.model.Customer;
import com.carlosgil.customer_service.infrastructure.controller.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }

    @Test
    void given_a_customer_with_a_valid_id_then_we_should_return_a_customer_with_valid_data() {

        // GIVEN
        Customer customer = Customer.builder()
                .id(6)
                .name("Carlos")
                .surname("Gil")
                .email("carlos.gil@example.com")
                .build();

        when(customerService.findCustomerById(6))
                .thenReturn(customer);

        // WHEN
        ResponseEntity<com.carlosgil.customer.api.model.Customer> response =
                customerController.getCustomerById(6);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals((int) response.getBody().getId(), customer.getId());
        assertEquals(response.getBody().getSurname(), customer.getSurname());
        assertEquals(response.getBody().getName(), customer.getName());
        assertEquals(response.getBody().getEmail(), customer.getEmail());

    }

    @Test
    void given_a_customer_with_a_valid_id_then_we_should_delete_a_customer() {
        // Given
        // When
        ResponseEntity<Void> deleteCustomerById = customerController.deleteCustomerById(5);
        // Then
        assertEquals(HttpStatus.NO_CONTENT, deleteCustomerById.getStatusCode());
        verify(customerService).deleteCustomer(5);
    }


    @Test
    void given_a_customer_with_data_then_we_should_create_a_customer() throws Exception {

        // GIVEN
        Customer customer = Customer.builder()
                .id(6)
                .name("Carlos")
                .surname("Gil")
                .email("carlos.gil@example.com")
                .build();

        when(customerService.createCustomer(any(Customer.class)))
                .thenReturn(customer);

        // WHEN
        mockMvc.perform(
                        post("/customer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                                "name": "Carlos",
                                "surname": "Gil",
                                "email": "carlos.gil@example.com"
                            }
                            """)
                )

                // THEN
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.name").value("Carlos"))
                .andExpect(jsonPath("$.surname").value("Gil"))
                .andExpect(jsonPath("$.email").value("carlos.gil@example.com"));

        verify(customerService).createCustomer(any(Customer.class));
    }

    @Test
    void given_a_customer_with_a_valid_data_then_we_should_update_customer_with_valid_data() throws Exception {
        // GIVEN
        Customer customer = Customer.builder()
                .id(6)
                .name("Carlos")
                .surname("Gil")
                .email("carlos.gil@example.com")
                .build();

        Customer customerUpdated = new Customer();
        customerUpdated.setId(6);
        customerUpdated.setName("David");
        customerUpdated.setSurname("Alonso");
        customerUpdated.setEmail("david.alonso@example.com");

        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest();

        customerUpdateRequest.setName("David");
        customerUpdateRequest.setSurname("Alonso");
        customerUpdateRequest.setEmail("david.alonso@example.com");

        when(customerService.findCustomerById(6))
                .thenReturn(customer);

        when(customerService.updateCustomer(any(Customer.class)))
                .thenReturn(customerUpdated);

        // WHEN
        mockMvc.perform(
                        patch("/customer/6")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                        {
                            "name": "David",
                            "surname": "Alonso",
                            "email": "david.alonso@example.com"
                        }
                        """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.name").value("David"))
                .andExpect(jsonPath("$.surname").value("Alonso"))
                .andExpect(jsonPath("$.email").value("david.alonso@example.com"));
        verify(customerService).updateCustomer(any(Customer.class));
    }

    }
