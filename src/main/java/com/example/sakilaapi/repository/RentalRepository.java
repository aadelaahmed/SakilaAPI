package com.example.sakilaapi.repository;

import com.example.sakilaapi.model.Rental;

public class RentalRepository extends BaseRepository<Rental, Integer> {
    public RentalRepository(){
        super(Rental.class);
    }
}
