package com.dzinevich.brewery.web.mappers;

import com.dzinevich.brewery.domain.Customer;
import com.dzinevich.brewery.web.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {DateMapper.class})
public interface CustomerMapper {
    @Mapping(source = "customerId", target = "id")
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(source = "id", target = "customerId")
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
