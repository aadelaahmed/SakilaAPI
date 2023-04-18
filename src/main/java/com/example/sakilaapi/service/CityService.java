package com.example.sakilaapi.service;

import com.example.sakilaapi.dto.city.CityDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.mapper.city.CityMapper;
import com.example.sakilaapi.model.City;
import com.example.sakilaapi.repository.BaseRepository;
import com.example.sakilaapi.repository.CityRepository;

public class CityService extends BaseService<City, CityDto> {
    CityRepository cityRepository;
    CityMapper cityMapper;
    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        super(cityRepository, cityMapper);
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    protected Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    protected Class<CityDto> getDtoClass() {
        return CityDto.class;
    }

    public CityDto getCityById(Integer id) {
        return cityRepository.getCityById(id);
    }


}
