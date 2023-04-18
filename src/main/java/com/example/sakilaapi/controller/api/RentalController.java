package com.example.sakilaapi.controller.api;
import com.example.sakilaapi.controller.request.FilmRentalRequest;
import com.example.sakilaapi.dto.rental.RentalDto;
import com.example.sakilaapi.dto.rental.RentalSummaryDto;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.mapper.rental.RentalMapper;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.repository.RentalRepository;
import com.example.sakilaapi.service.actor.ActorServiceImpl;
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
    //TODO -> USE IOC spring container HERE
    private final RentalServiceImpl service = new RentalServiceImpl(
            new RentalRepository(), RentalMapper.INSTANCE
    );
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(FilmRentalRequest filmRentalRequest) {
        RentalSummaryDto rentalSummaryDto = service.createRental(filmRentalRequest);
        if (rentalSummaryDto != null){
            return Response.ok(rentalSummaryDto).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create this rental object").build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<RentalSummaryDto> summaries = service.getRentalSummaries();
        System.out.println(summaries.stream().limit(3));
        GenericEntity entity = new GenericEntity<>(summaries) {
        };
        return Response.ok(entity).build();
    }
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentalById(@PathParam("id") Integer id) {
        RentalSummaryDto summary = service.getRentalSummaryById(id);
        return Response.ok(summary).build();
    }
}
