package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.StaffDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface StaffMapper extends BaseMapper<StaffDto, Staff> {
}