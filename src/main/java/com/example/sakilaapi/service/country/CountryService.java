package com.example.sakilaapi.service.country;

import com.example.sakilaapi.dto.CountryDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.mapper.country.CountryMapper;
import com.example.sakilaapi.model.Country;
import com.example.sakilaapi.repository.BaseRepository;
import com.example.sakilaapi.repository.CountryRepository;
import com.example.sakilaapi.service.BaseService;

public class CountryService extends BaseService<Country, CountryDto> {
    CountryRepository countryRepository;
    CountryMapper countryMapper;
    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        super(countryRepository, countryMapper);
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    protected Class<Country> getEntityClass() {
        return Country.class;
    }

    @Override
    protected Class<CountryDto> getDtoClass() {
        return CountryDto.class;
    }
}
