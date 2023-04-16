package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.RentalDto;
import com.example.sakilaapi.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {InventoryMapper.class, StaffMapper.class})
public interface RentalMapper extends BaseMapper<RentalDto, Rental> {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);
}