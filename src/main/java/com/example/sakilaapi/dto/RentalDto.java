package com.example.sakilaapi.dto;

import com.example.sakilaapi.model.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.example.sakilaapi.model.Rental} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto implements Serializable {
    private Integer id;
    @NotNull
    private Instant rentalDate;
    @NotNull
    private InventoryDto inventoryDto;
    @NotNull
    private CustomerDto customerDto;
//    private Instant returnDate;
    @NotNull
    private StaffDto staffDto;
    @NotNull
    private Instant lastUpdate;
}