package com.example.sakilaapi.controller;
import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.dto.RentalDto;
import com.example.sakilaapi.service.rental.RentalService;
import com.example.sakilaapi.service.rental.RentalServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;
import java.util.Optional;

@Path("/rentals")
public class RentalController {
    private final RentalService service = new RentalServiceImpl();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(RentalDto rentalDto) {
        Optional<RentalDto> optionalRentalDto = Optional.ofNullable(service.createRental(rentalDto));
        if (optionalRentalDto.isPresent()){
            return Response.ok(optionalRentalDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create this rental object").build();
    }
}
