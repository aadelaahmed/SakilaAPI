package com.example.sakilaapi.service.category;

import com.example.sakilaapi.dto.CategoryDto;
import com.example.sakilaapi.mapper.CategoryMapper;
import com.example.sakilaapi.model.Category;
import com.example.sakilaapi.repository.CategoryRepository;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryServiceImpl() {
        categoryRepository = new CategoryRepository();
        categoryMapper = CategoryMapper.INSTANCE;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.getAll();
        return categoryMapper.toDto(categories);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.getById(id);
        if (optionalCategory.isPresent()) {
            return categoryMapper.toDto(optionalCategory.get());
        }
        throw new NotFoundException("There is no category with this id");
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.getById(id);
        if (optionalCategory.isPresent()) {
            Category category = categoryMapper.toEntity(categoryDto);
            category.setName(categoryDto.getName());
            categoryRepository.update(category);
            return categoryMapper.toDto(category);
        }
        throw new NotFoundException("There is no category with this id");
    }

    @Override
    public void deleteCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.getById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.delete(optionalCategory.get());
        }
        throw new NotFoundException("There is no category with this id");
    }


}

