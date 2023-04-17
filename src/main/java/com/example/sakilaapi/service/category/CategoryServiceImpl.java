package com.example.sakilaapi.service.category;

import com.example.sakilaapi.dto.CategoryDto;
import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.mapper.CategoryMapper;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.model.Category;
import com.example.sakilaapi.repository.BaseRepository;
import com.example.sakilaapi.repository.CategoryRepository;
import com.example.sakilaapi.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl extends BaseService<Category,CategoryDto> {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        super(categoryRepository, categoryMapper);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    protected Class<CategoryDto> getDtoClass() {
        return CategoryDto.class;
    }

    public boolean addFilmToCategory(Integer filmId, Integer categoryId) {
        return categoryRepository.addFilmToCategory(filmId,categoryId);
    }


}

