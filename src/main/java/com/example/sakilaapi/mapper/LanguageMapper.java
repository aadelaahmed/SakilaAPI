package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.LanguageDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.model.Language;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {FilmMapper.class, FilmMapper.class})
public interface LanguageMapper extends BaseMapper<LanguageDto, Language> {
    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    @AfterMapping
    default void linkFilms(@MappingTarget Language language) {
        language.getFilms().forEach(film -> film.setLanguage(language));
    }

    @AfterMapping
    default void linkOrgLangFilms(@MappingTarget Language language) {
        language.getOrgLangFilms().forEach(orgLangFilm -> orgLangFilm.setOriginalLanguage(language));
    }
}