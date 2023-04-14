package com.example.sakilaapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.example.sakilaapi.model.Film} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto implements Serializable {
    private Integer id;
    @Size(max = 128)
    @NotNull
    private String title;
    private String description;
    private Integer releaseYear;
    private Short rentalDuration;
    @NotNull
    private BigDecimal rentalRate;
    private Integer length;
    @Size(max = 5)
    private String rating;
}