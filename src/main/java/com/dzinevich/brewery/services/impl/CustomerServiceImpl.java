package com.dzinevich.brewery.services.impl;

import com.dzinevich.brewery.services.CustomerService;
import com.dzinevich.brewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Beerman")
                .build();
    }

    @Override
    public CustomerDto addNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("New Beerman")
                .build();
    }

    @Override
    public void updateCustomer(UUID id, CustomerDto customerDto) {
        log.debug("Updating a customer.... id={}", id);
    }

    @Override
    public void deleteCustomer(UUID id) {
        log.debug("Deleting a customer.... id={}", id);
    }
}
