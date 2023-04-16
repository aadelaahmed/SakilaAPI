package com.example.sakilaapi.mapper;

import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Address;
import com.example.sakilaapi.model.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface AddressMapper extends BaseMapper<AddressDto, Address> {
}