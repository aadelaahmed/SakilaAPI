package com.example.sakilaapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.example.sakilaapi.model.Category} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {
    private Short id;
    @Size(max = 25)
    @NotNull
    private String name;
    @NotNull
    private Instant lastUpdate;
    private Set<FilmCategoryDto> filmCategories = new LinkedHashSet<>();
}