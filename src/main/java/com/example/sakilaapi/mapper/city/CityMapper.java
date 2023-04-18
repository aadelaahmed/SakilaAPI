package com.example.sakilaapi.mapper.city;

import com.example.sakilaapi.dto.city.CityDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CityMapper extends BaseMapper<CityDto, City> {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
}