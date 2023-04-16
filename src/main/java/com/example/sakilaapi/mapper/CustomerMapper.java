package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.CustomerDto;
import com.example.sakilaapi.mapper.AddressMapper;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.mapper.StoreMapper;
import com.example.sakilaapi.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {StoreMapper.class, AddressMapper.class})
public interface CustomerMapper extends BaseMapper<CustomerDto, Customer> {
}