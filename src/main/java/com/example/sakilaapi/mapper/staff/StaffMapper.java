package com.example.sakilaapi.mapper.staff;

import com.example.sakilaapi.dto.staff.StaffDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Staff;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface StaffMapper extends BaseMapper<StaffDto, Staff> {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "store.id",source = "storeId")
    void partialUpdate(@MappingTarget Staff entity,StaffDto dto);
}