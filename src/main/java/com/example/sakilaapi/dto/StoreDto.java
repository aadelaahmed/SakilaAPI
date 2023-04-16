package com.example.sakilaapi.dto;

import com.example.sakilaapi.model.AddressDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.example.sakilaapi.model.Store} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto implements Serializable {
    private Short id;
    @NotNull
    private StaffDto managerStaff;
    @NotNull
    private AddressDto address;
    @NotNull
    private Instant lastUpdate;
}