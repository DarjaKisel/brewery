package com.dzinevich.brewery.services;

import com.dzinevich.brewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID id);
    CustomerDto addNewCustomer(CustomerDto customerDto);
    void updateCustomer(UUID id, CustomerDto customerDto);
    void deleteCustomer(UUID id);
}
