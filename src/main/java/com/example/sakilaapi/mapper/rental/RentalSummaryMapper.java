package com.example.sakilaapi.mapper.rental;

import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.dto.rental.RentalSummaryDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Customer;
import com.example.sakilaapi.model.Rental;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface RentalSummaryMapper extends BaseMapper<RentalSummaryDto, Rental> {
    RentalSummaryMapper INSTANCE = Mappers.getMapper(RentalSummaryMapper.class);

    @Mapping(target = "customerEmail",source = "customer.email")
    @Mapping(target = "storeId",source = "staff.store.id")
    @Mapping(target = "id",source = "id")
    @Mapping(target = "rentalDate",source = "rentalDate")
    @Mapping(target = "lastUpdate",source = "lastUpdate")
    @Mapping(target = "returnDate",source = "returnDate")
    @Mapping(target = "staffUserName",source = "staff.username")
    RentalSummaryDto toDto(Rental rental);


    @Mapping(source = "customerEmail",target = "customer.email")
    @Mapping(source = "storeId",target = "staff.store.id")
    @Mapping(source = "id",target = "id")
    @Mapping(source = "rentalDate",target = "rentalDate")
    @Mapping(source = "lastUpdate",target = "lastUpdate")
    @Mapping(source = "returnDate",target = "returnDate")
    @Mapping(source = "staffUserName",target = "staff.username")
    Rental toEntity(RentalSummaryDto rentalSummaryDto);


    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "customerEmail",target = "customer.email")
    @Mapping(source = "storeId",target = "staff.store.id")
    @Mapping(source = "id",target = "id")
    @Mapping(source = "rentalDate",target = "rentalDate")
    @Mapping(source = "lastUpdate",target = "lastUpdate")
    @Mapping(source = "returnDate",target = "returnDate")
    @Mapping(source = "staffUserName",target = "staff.username")
    void partialUpdate(@MappingTarget Rental rental, RentalSummaryDto rentalSummaryDto);
}