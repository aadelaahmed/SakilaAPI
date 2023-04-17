package com.example.sakilaapi.dto.staff;

import com.example.sakilaapi.dto.store.StoreDto;
import com.example.sakilaapi.model.AddressDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffSummaryDto implements Serializable {
    private Short id;
    @Size(max = 45)
    @NotNull
    private String firstName;
    @Size(max = 45)
    @NotNull
    private String lastName;
    private String staffAddress;
    private String staffPhone;
    private String staffCity;
    @Size(max = 50)
    private String email;
    @NotNull
    private Boolean active = false;
    @NotNull
    private String username;
    private String storeAddress;
    private String storePhone;
    private String storeCity;
}