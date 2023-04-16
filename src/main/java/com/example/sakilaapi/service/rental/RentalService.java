package com.example.sakilaapi.service.rental;

import com.example.sakilaapi.dto.RentalDto;
import com.example.sakilaapi.model.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<RentalDto> getAllRentals();
    Optional<RentalDto> getRentalById(Integer id);
    RentalDto createRental(RentalDto rental);
    RentalDto updateRental(RentalDto rental);
    void deleteRentalById(Integer id);
}

