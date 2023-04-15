package com.example.sakilaapi.controller;


import com.example.sakilaapi.dto.CategoryDto;
import com.example.sakilaapi.service.category.CategoryService;
import com.example.sakilaapi.service.category.CategoryServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController() {
        categoryService = new CategoryServiceImpl();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        GenericEntity<List<CategoryDto>> entity = new GenericEntity<>(categoryDtos) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") Integer id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return Response.ok(categoryDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryDto categoryDto) {
        CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);
        return Response.ok(createdCategoryDto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") Integer id, CategoryDto categoryDto) {
        CategoryDto updatedCategoryDto = categoryService.updateCategory(id, categoryDto);
        return Response.ok(updatedCategoryDto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") Integer id) {
        categoryService.deleteCategory(id);
        return Response.noContent().build();
    }
}
