package com.example.sakilaapi.service.rental;

import com.example.sakilaapi.dto.RentalDto;
import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.model.Rental;
import com.example.sakilaapi.mapper.RentalMapper;
import com.example.sakilaapi.repository.RentalRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RentalServiceImpl implements RentalService{
    private RentalRepository rentalRepository;
    private RentalMapper rentalMapper;
    private static final Logger logger = LogManager.getLogManager().getLogger("RentalServiceImpl");

    public RentalServiceImpl() {
        this.rentalRepository = new RentalRepository();
        this.rentalMapper = RentalMapper.INSTANCE;
    }
    @Override
    public List<RentalDto> getAllRentals() {
        return null;
    }

    @Override
    public Optional<RentalDto> getRentalById(Integer id) {
        return Optional.empty();
    }

    @Override
    public RentalDto createRental(RentalDto rentalDto) {
        //firstly,ensures that there is no rental with this id in db.
        Optional<Rental> optionalExistedRental = null;
        if (rentalDto.getId() != null) {
            optionalExistedRental = rentalRepository.getById(rentalDto.getId());
            if (optionalExistedRental.isPresent())
                throw new EntityAlreadyExistException("Rental already exists with id: " + rentalDto.getId());
        }
        Rental rental = rentalMapper.toEntity(rentalDto);
        rental.setId(null);
        System.out.println("rental obj which is saved in db -> " + rental.toString());
        return rentalMapper.toDto(rentalRepository.save(rental));
    }

    @Override
    public RentalDto updateRental(RentalDto rental) {
        return null;
    }

    @Override
    public void deleteRentalById(Integer id) {

    }
}
