package com.example.sakilaapi.service.category;

import com.example.sakilaapi.dto.CategoryDto;
import com.example.sakilaapi.dto.FilmDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Integer id);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Integer id, CategoryDto categoryDto);
    void deleteCategory(Integer id);
    boolean addFilmToCategory(Integer filmId,Integer categoryId);
}
