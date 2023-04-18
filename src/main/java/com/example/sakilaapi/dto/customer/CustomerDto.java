package com.example.sakilaapi.dto.customer;

import com.example.sakilaapi.dto.PaymentDto;
import com.example.sakilaapi.dto.rental.RentalDto;
import com.example.sakilaapi.dto.store.StoreDto;
import com.example.sakilaapi.model.AddressDto;
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
 * A DTO for the {@link com.example.sakilaapi.model.Customer} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private Integer id;
    @NotNull
    private StoreDto store;
    @Size(max = 45)
    @NotNull
    private String firstName;
    @Size(max = 45)
    @NotNull
    private String lastName;
    @Size(max = 50)
    private String email;
    @NotNull
    private AddressDto address;
    @NotNull
    private Boolean active = false;
    @NotNull
    private Instant createDate;
    private Instant lastUpdate;
    private Set<PaymentDto> payments = new LinkedHashSet<>();
    private Set<RentalDto> rentals = new LinkedHashSet<>();
}