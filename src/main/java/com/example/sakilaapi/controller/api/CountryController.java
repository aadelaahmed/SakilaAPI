package com.example.sakilaapi.controller.api;

import com.example.sakilaapi.dto.CountryDto;
import com.example.sakilaapi.dto.PaymentDto;
import com.example.sakilaapi.dto.customer.CustomerDto;
import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.mapper.country.CountryMapper;
import com.example.sakilaapi.mapper.customer.CustomerMapper;
import com.example.sakilaapi.repository.CountryRepository;
import com.example.sakilaapi.repository.CustomerRepository;
import com.example.sakilaapi.service.country.CountryService;
import com.example.sakilaapi.service.customer.CustomerServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/countries")
public class CountryController {
    //TODO -> USE IOC spring container HERE

    private final CountryService countryService = new CountryService(
            new CountryRepository(),
            CountryMapper.INSTANCE
    );



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCountries() {
        List<CountryDto> countries = countryService.getAll();
        GenericEntity<List<CountryDto>> entity = new GenericEntity<>(countries) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountryById(@PathParam("id") int id) {
        CountryDto countryDto = countryService.getById(id);
        if (countryDto != null) {
            return Response.ok(countryDto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Country with id " + id + " not found").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCountry(CountryDto countryDto) {
        Optional<CountryDto> optionalCountryDto = Optional.ofNullable(countryService.create(countryDto, countryDto.getId()));
        if (optionalCountryDto.isPresent()) {
            return Response.ok(optionalCountryDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create country").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, CountryDto countryDto) {
        countryDto.setId(id);
        /*Optional<CountryDto> optionalCountryDto = Optional.of(countryService.update(id,countryDto));
        if (optionalCountryDto.isPresent()){
            return Response.ok(optionalCountryDto.get()).build();
        }*/

        boolean res = countryService.update(id,countryDto);
        if (res) {
            return Response.ok("Country was updated successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("can't update this Country").build();
    }

    /*@DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        countryService.deleteById(id);
        return Response.ok("Country was deleted successfully").build();
    }*/
}
