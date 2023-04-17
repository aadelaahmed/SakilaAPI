package com.example.sakilaapi.mapper;

import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.FilmCategoryId;
import com.example.sakilaapi.model.FilmCategoryIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)

public interface FilmCategoryIdMapper extends BaseMapper<FilmCategoryIdDto, FilmCategoryId> {
}