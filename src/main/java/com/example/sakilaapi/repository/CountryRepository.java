package com.example.sakilaapi.repository;

import com.example.sakilaapi.model.Country;

public class CountryRepository extends BaseRepository<Country,Integer> {

    public CountryRepository() {
        super(Country.class);
    }

}
