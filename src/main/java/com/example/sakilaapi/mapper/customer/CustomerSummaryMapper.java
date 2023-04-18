package com.example.sakilaapi.mapper.customer;

import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.mapper.*;
import com.example.sakilaapi.mapper.rental.RentalMapper;
import com.example.sakilaapi.mapper.store.StoreMapper;
import com.example.sakilaapi.model.Customer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {StoreMapper.class, AddressMapper.class, PaymentMapper.class, RentalMapper.class})
public interface CustomerSummaryMapper extends BaseMapper<CustomerSummaryDto, Customer> {
    CustomerSummaryMapper INSTANCE = Mappers.getMapper(CustomerSummaryMapper.class);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "storeAddress",source = "store.address.address")
    @Mapping(target = "postalCode",source = "address.postalCode")
    @Mapping(target = "email",source = "email")
    @Mapping(target = "firstName",source = "firstName")
    @Mapping(target = "lastName",source = "lastName")
    @Mapping(target = "active",source = "active")
    @Mapping(target = "city",source = "address.city.city")
    @Mapping(target = "address",source = "address.address")
    CustomerSummaryDto toDto(Customer customer);


    @Mapping(source = "id",target = "id")
    @Mapping(source = "storeAddress",target = "store.address.address")
    @Mapping(source = "postalCode",target = "address.postalCode")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "firstName",target = "firstName")
    @Mapping(source = "lastName",target = "lastName")
    @Mapping(source = "active",target = "active")
    @Mapping(source = "city",target = "address.city.city")
    @Mapping(source = "address",target = "address.address")
    Customer toEntity(CustomerSummaryDto customerSummaryDto);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "id",target = "id")
    @Mapping(source = "storeAddress",target = "store.address.address")
    @Mapping(source = "postalCode",target = "address.postalCode")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "firstName",target = "firstName")
    @Mapping(source = "lastName",target = "lastName")
    @Mapping(source = "active",target = "active")
    @Mapping(source = "city",target = "address.city.city")
    @Mapping(source = "address",target = "address.address")
    void partialUpdate(@MappingTarget Customer customer, CustomerSummaryDto customerSummaryDto);
}
