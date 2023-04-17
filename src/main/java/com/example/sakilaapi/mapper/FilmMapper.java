package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface FilmMapper extends BaseMapper<FilmDto, Film> {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);
}