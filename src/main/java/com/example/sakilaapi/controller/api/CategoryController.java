package com.example.sakilaapi.controller.api;


import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.dto.CategoryDto;
import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.mapper.CategoryMapper;
import com.example.sakilaapi.repository.CategoryRepository;
import com.example.sakilaapi.service.category.CategoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Path("/categories")
public class CategoryController {
    //TODO -> USE IOC spring container HERE

    private final CategoryService categoryService = new CategoryService(
            new CategoryRepository(), CategoryMapper.INSTANCE
    );


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.getAll();
        GenericEntity<List<CategoryDto>> entity = new GenericEntity<>(categoryDtos) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryById(@PathParam("id") Integer id) {
        CategoryDto categoryDto = categoryService.getById(id);
        return Response.ok(categoryDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryDto categoryDto) {
        categoryDto.setId(null);
        categoryDto.setLastUpdate(Instant.now());
        Optional<CategoryDto> optionalCategoryDto = Optional.ofNullable(categoryService.createByName(categoryDto,"name",categoryDto.getName()));
        if (optionalCategoryDto.isPresent()){
            return Response.ok(optionalCategoryDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create category").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") Integer id, CategoryDto categoryDto) {
        categoryDto.setLastUpdate(Instant.now());
        categoryDto.setId(null);
        CategoryDto res = categoryService.update(id,categoryDto);
        if (res!=null) {
            return Response.ok(res).build();
        }else
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("can't update this Category").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") Integer id) {
        categoryService.deleteById(id);
        return Response.ok("Category was deleted successfully").build();
    }
    @POST
    @Path("{categoryId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFilmToCategory(@PathParam("categoryId") Integer categoryId,@QueryParam("filmId") int filmId) {
        boolean isAdded = categoryService.addFilmToCategory(filmId, categoryId);
        if (isAdded)
            return Response.ok("The film was added successfully to the category with id: "+categoryId).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
