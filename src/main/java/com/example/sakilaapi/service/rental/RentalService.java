package com.example.sakilaapi.service.rental;

import com.example.sakilaapi.controller.request.FilmRentalRequest;
import com.example.sakilaapi.dto.rental.RentalDto;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    List<RentalDto> getAllRentals();
    Optional<RentalDto> getRentalById(Integer id);
    RentalDto createRental(FilmRentalRequest filmRentalRequest);
    RentalDto updateRental(RentalDto rental);
    void deleteRentalById(Integer id);
}

