package com.example.sakilaapi.service.rental;

import com.example.sakilaapi.controller.request.FilmRentalRequest;
import com.example.sakilaapi.dto.rental.RentalDto;
import com.example.sakilaapi.dto.rental.RentalSummaryDto;
import com.example.sakilaapi.model.*;
import com.example.sakilaapi.mapper.rental.RentalMapper;
import com.example.sakilaapi.repository.*;
import com.example.sakilaapi.service.BaseService;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RentalService extends BaseService<Rental, RentalDto> {
    private RentalRepository rentalRepository;

    private RentalMapper rentalMapper;
    private static final Logger logger = LogManager.getLogManager().getLogger("RentalServiceImpl");

    public RentalService(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        super(rentalRepository, rentalMapper);
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    @Override
    protected Class<Rental> getEntityClass() {
        return Rental.class;
    }

    @Override
    protected Class<RentalDto> getDtoClass() {
        return RentalDto.class;
    }

    /*@Override
    public List<RentalDto> getAllRentals() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Rental> rentals = rentalRepository.getAll(entityManager);
                    System.out.println("rental entities ->" + rentals.get(2).toString());
                    return rentalMapper.toDto(rentals);
                }
        );
        List<Rental> rentals = rentalRepository.getRentalSummaries();
        System.out.println("rental entities ->" + rentals.get(2).toString());
        return rentalMapper.toDto(rentals);
    }*/
    public List<RentalSummaryDto> getRentalSummaries(){
        List<RentalSummaryDto> summaries = rentalRepository.getRentalSummaries();
        return summaries;
    }


    public RentalSummaryDto getRentalSummaryById(Integer rentalId){
        return rentalRepository.getRentalSummaryById(rentalId);
    }



    public RentalSummaryDto createRental(FilmRentalRequest filmRentalRequest) {
        return rentalRepository.save(filmRentalRequest);
    }

}
