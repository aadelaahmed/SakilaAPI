package com.example.sakilaapi.dto;

import com.example.sakilaapi.dto.store.StoreDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.example.sakilaapi.model.Inventory} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto implements Serializable {
    private Integer id;
    @NotNull
    private FilmDto film;
    @NotNull
    private StoreDto store;
    @NotNull
    private Instant lastUpdate;
}