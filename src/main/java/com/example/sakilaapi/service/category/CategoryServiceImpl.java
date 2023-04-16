package com.example.sakilaapi.service.category;

import com.example.sakilaapi.dto.CategoryDto;
import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.mapper.CategoryMapper;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.model.Category;
import com.example.sakilaapi.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private FilmMapper filmMapper;

    public CategoryServiceImpl() {
        categoryRepository = new CategoryRepository();
        categoryMapper = CategoryMapper.INSTANCE;
        filmMapper = FilmMapper.INSTANCE;
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

        //firstly,ensures that there is no actor with this id in db.
        Optional<Category> optionalCategory = null;
        if (categoryDto.getId() != null) {
            optionalCategory = categoryRepository.getById(categoryDto.getId());
            if (optionalCategory.isPresent())
                throw new EntityAlreadyExistException("Category already exists with id: " + categoryDto.getId());
        }
        Category category = categoryMapper.toEntity(categoryDto);
        category.setId(null);
        System.out.println("category obj which is saved in db -> " + category.toString());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.getById(id);
        if (optionalCategory.isPresent()) {

            Category oldCategory = optionalCategory.get();
            Category newUpdatedCategory = cutsomMapperForUpdatingCategory(oldCategory, categoryDto);
            System.out.println("before merging the new updated category ->" + newUpdatedCategory.toString());
            return categoryMapper.toDto(categoryRepository.update(newUpdatedCategory));
            /*Category category = categoryMapper.toEntity(categoryDto);
            category.setName(categoryDto.getName());
            categoryRepository.update(category);
            return categoryMapper.toDto(category);*/
        } else
            throw new EntityNotFoundException("There is no category with this id");
    }

    private Category cutsomMapperForUpdatingCategory(Category oldCategory, CategoryDto categoryDto) {
        Category updatedCategory = new Category();
        updatedCategory.setId(oldCategory.getId());
        updatedCategory.setName(categoryDto.getName() != null ? categoryDto.getName() : oldCategory.getName());
        updatedCategory.setLastUpdate(categoryDto.getLastUpdate() != null ? categoryDto.getLastUpdate() : categoryDto.getLastUpdate());
        updatedCategory.setFilmCategories(oldCategory.getFilmCategories());
        return updatedCategory;
    }

    @Override
    public void deleteCategory(Integer id) {
        /*Optional<Category> optionalCategory = categoryRepository.getById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
        }*/
        categoryRepository.deleteById(id);
        //throw new EntityNotFoundException("There is no category with this id");
    }
    @Override
    public boolean addFilmToCategory(Integer filmId,Integer categoryId) {
        return categoryRepository.addFilmToCategory(filmId,categoryId);
    }


}

