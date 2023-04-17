package com.example.sakilaapi.mapper.customer;

import com.example.sakilaapi.dto.customer.CustomerDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Customer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CustomerMapper extends BaseMapper<CustomerDto, Customer> {


    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    @AfterMapping
    default void linkPayments(@MappingTarget Customer customer) {
        customer.getPayments().forEach(payment -> payment.setCustomer(customer));
    }

    @AfterMapping
    default void linkRentals(@MappingTarget Customer customer) {
        customer.getRentals().forEach(rental -> rental.setCustomer(customer));
    }
}