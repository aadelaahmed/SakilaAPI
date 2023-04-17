package com.example.sakilaapi.repository;

import com.example.sakilaapi.controller.request.FilmRentalRequest;
import com.example.sakilaapi.model.*;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class RentalRepository extends BaseRepository<Rental, Integer> {
    public RentalRepository(){
        super(Rental.class);
    }


    public Rental save(FilmRentalRequest filmRentalRequest) {
        return Database.doInTransaction(entityManager -> {
            //firstly,ensures that there is inventory with this id in db.
            Integer filmId = filmRentalRequest.getFilmId();
            Film film =entityManager.find(Film.class,filmId);
            if (film == null)
                throw new EntityNotFoundException("Can't find film of id: " + filmId);

            //then,ensures that there is Customer with this id in db.
            Integer customerId = filmRentalRequest.getCustomerId();
            Customer customer = entityManager.find(Customer.class,customerId);
            if (customer == null)
                throw new EntityNotFoundException("Can't find customer of id: " + customerId);
            //then,ensures that there is Staff with this id in db.
            Integer staffId = filmRentalRequest.getStaffId();
            Staff staff = entityManager.find(Staff.class,staffId);
            if (staff == null)
                throw new EntityNotFoundException("Can't find staff of id: " + staffId);
            //create inventory object to set it in the rental.
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(customer.getStore());
            inventory.setLastUpdate(Instant.now());
            entityManager.persist(inventory);
            //now, create the rental object to be saved in db.
            Rental rental = new Rental();
            rental.setCustomer(customer);
            rental.setLastUpdate(Instant.now());
            rental.setStaff(staff);
            rental.setInventory(inventory);
            Instant rentalDate = Instant.now();
            rental.setRentalDate(rentalDate);
            Instant expectedReturnDate = getExpectedReturnDate(rentalDate);
            rental.setReturnDate(expectedReturnDate);
            rental = entityManager.merge(rental);
            return rental;
        });
    }
    private Instant getExpectedReturnDate(Instant rentalDate) {
        return rentalDate.plus(Duration.ofDays(7));
    }
}
