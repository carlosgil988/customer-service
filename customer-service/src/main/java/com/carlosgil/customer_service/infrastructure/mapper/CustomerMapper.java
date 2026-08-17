package com.carlosgil.customer_service.infrastructure.mapper;


import com.carlosgil.customer_service.domain.model.Customer;

public class CustomerMapper {


    public com.carlosgil.customer.api.model.Customer  mapToApi(Customer customer){
        com.carlosgil.customer.api.model.Customer customerApi = new com.carlosgil.customer.api.model.Customer();
        customerApi.setId(customer.getId());
        customerApi.setName(customer.getName());
        customerApi.setSurname(customer.getSurname());
        customerApi.setEmail(customer.getEmail());
        return customerApi;
    }
}
