package com.example.sakilaapi.service.category;

import com.example.sakilaapi.dto.CategoryDto;
import com.example.sakilaapi.mapper.CategoryMapper;
import com.example.sakilaapi.model.Category;
import com.example.sakilaapi.repository.CategoryRepository;
import com.example.sakilaapi.service.BaseService;

public class CategoryService extends BaseService<Category,CategoryDto> {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
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

