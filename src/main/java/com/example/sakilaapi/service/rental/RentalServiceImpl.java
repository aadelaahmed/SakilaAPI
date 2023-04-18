package com.example.sakilaapi.service.rental;

import com.example.sakilaapi.controller.request.FilmRentalRequest;
import com.example.sakilaapi.dto.rental.RentalDto;
import com.example.sakilaapi.model.*;
import com.example.sakilaapi.mapper.rental.RentalMapper;
import com.example.sakilaapi.repository.*;
import com.example.sakilaapi.util.Database;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RentalServiceImpl implements RentalService {
    private RentalRepository rentalRepository;

    private RentalMapper rentalMapper;
    private static final Logger logger = LogManager.getLogManager().getLogger("RentalServiceImpl");

    public RentalServiceImpl() {
        this.rentalRepository = new RentalRepository();
        this.rentalMapper = RentalMapper.INSTANCE;
    }

    @Override
    public List<RentalDto> getAllRentals() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Rental> rentals = rentalRepository.getAll(entityManager);
                    System.out.println("rental entities ->" + rentals.get(2).toString());
                    return rentalMapper.toDto(rentals);
                }
        );
        /*List<Rental> rentals = rentalRepository.getAll();
        System.out.println("rental entities ->" + rentals.get(2).toString());
        return rentalMapper.toDto(rentals);*/
    }

    @Override
    public Optional<RentalDto> getRentalById(Integer id) {
        return Optional.empty();
    }


    @Override
    public RentalDto createRental(FilmRentalRequest filmRentalRequest) {
        /*//firstly,ensures that there is inventory with this id in db.
        Integer filmId = filmRentalRequest.getFilmId();
        Optional<Film> optionalFilm = filmRepository.getById(filmId);
        if (!optionalFilm.isPresent())
            throw new EntityNotFoundException("Can't find film of id: " + filmId);

        //then,ensures that there is Customer with this id in db.
        Integer customerId = filmRentalRequest.getCustomerId();
        Optional<Customer> optionalCustomer = customerRepository.getById(customerId);
        if (!optionalCustomer.isPresent())
            throw new EntityNotFoundException("Can't find customer of id: " + customerId);

        //then,ensures that there is Staff with this id in db.
        Integer staffId = filmRentalRequest.getStaffId();
        Optional<Staff> optionalStaff = staffRepository.getById(staffId);
        if (!optionalStaff.isPresent())
            throw new EntityNotFoundException("Can't find staff of id: " + staffId);
        //create inventory object to set it in the rental.
        Inventory inventory = new Inventory();
        inventory.setFilm(optionalFilm.get());
        inventory.setStore(customerRepository.getStoreByCustomerId(customerId));
        //now, create the rental object to be saved in db.
        Rental rental = new Rental();
        rental.setCustomer(optionalCustomer.get());
        rental.setStaff(optionalStaff.get());
        rental.setInventory(inventory);
        Instant rentalDate = Instant.now();
        rental.setRentalDate(rentalDate);
        Instant expectedReturnDate = getExpectedReturnDate(rentalDate);
        rental.setReturnDate(expectedReturnDate);*/
        Rental newInsertedRental = rentalRepository.save(filmRentalRequest);
        System.out.println("rental obj which is saved in db -> " + newInsertedRental.toString());
        return rentalMapper.toDto(newInsertedRental);
    }

    private Instant getExpectedReturnDate(Instant rentalDate) {
        return rentalDate.plus(Duration.ofDays(7));
    }



    @Override
    public RentalDto updateRental(RentalDto rental) {
        return null;
    }

    @Override
    public void deleteRentalById(Integer id) {

    }
}
