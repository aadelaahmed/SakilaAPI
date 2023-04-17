package com.example.sakilaapi.mapper.country;

import com.example.sakilaapi.dto.CountryDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CountryMapper extends BaseMapper<CountryDto, Country> {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
}