package com.example.sakilaapi.dto;

import com.example.sakilaapi.model.AddressDto;
import com.example.sakilaapi.model.Store;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.example.sakilaapi.model.Staff} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto implements Serializable {
    private Short id;
    @Size(max = 45)
    @NotNull
    private String firstName;
    @Size(max = 45)
    @NotNull
    private String lastName;
    @NotNull
    private AddressDto address;
    private byte[] picture;
    @Size(max = 50)
    private String email;
    @NotNull
    private Boolean active = false;
    @Size(max = 16)
    @NotNull
    private String username;
    @Size(max = 40)
    private String password;
    @NotNull
    private Instant lastUpdate;
    private StoreDto storeMng;
    @NotNull
    private Store store;
}