package com.example.sakilaapi.repository;

import com.example.sakilaapi.model.Category;

public class CategoryRepository extends BaseRepository<Category,Integer> {

    public CategoryRepository() {
        super(Category.class);
    }

}
