package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.CategoryDto;
import com.example.sakilaapi.model.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {FilmCategoryMapper.class})
public interface CategoryMapper extends BaseMapper<CategoryDto, Category> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @AfterMapping
    default void linkFilmCategories(@MappingTarget Category category) {
        category.getFilmCategories().forEach(filmCategory -> filmCategory.setCategory(category));
    }
}