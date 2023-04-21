package com.example.sakilaapi.dto.store;

import com.example.sakilaapi.dto.staff.StaffDto;
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
    private Integer id;
    @NotNull
    private Integer managerStaffId;
    @NotNull
    private AddressDto address;
    @NotNull
    private Instant lastUpdate;
}