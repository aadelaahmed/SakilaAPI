package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.StoreDto;
import com.example.sakilaapi.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {StaffMapper.class, AddressMapper.class})
public interface StoreMapper extends BaseMapper<StoreDto, Store> {
}