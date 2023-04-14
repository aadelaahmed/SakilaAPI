package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.FilmCategoryDto;
import com.example.sakilaapi.model.FilmCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {FilmCategoryIdMapper.class, FilmMapper.class})
public interface FilmCategoryMapper extends BaseMapper<FilmCategoryDto, FilmCategory> {
}