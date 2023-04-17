package com.example.sakilaapi.dto.store;

import com.example.sakilaapi.dto.StaffDto;
import com.example.sakilaapi.model.AddressDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreSummaryDto implements Serializable {
    private Short id;
    private int managerId;
    private String managerFirstName;
    private String managerLastName;
    private String address;
    private String city;
    private String phone;
    private String postalCode;
}