package com.example.sakilaapi.model;

import com.example.sakilaapi.dto.RentalDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.mapper.InventoryMapper;
import com.example.sakilaapi.mapper.StaffMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {InventoryMapper.class, CustomerMapper.class, StaffMapper.class})
public interface RentalMapper extends BaseMapper<RentalDto, Rental> {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);
}