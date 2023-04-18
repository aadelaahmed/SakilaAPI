package com.example.sakilaapi.controller.api;


import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.dto.city.CityDto;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.mapper.city.CityMapper;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.repository.CityRepository;
import com.example.sakilaapi.service.CityService;
import com.example.sakilaapi.service.actor.ActorServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/cities")
public class CityController {
    //TODO -> USE IOC spring container HERE
    private final CityService service = new CityService(
            new CityRepository(), CityMapper.INSTANCE
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<CityDto> cityDtos = service.getAll();
        System.out.println(cityDtos.stream().limit(3));
        GenericEntity entity = new GenericEntity<>(cityDtos) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Optional<CityDto> optionalCityDto = Optional.ofNullable(service.getCityById(id));
        return Response.ok().entity(
                optionalCityDto.get()
        ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(CityDto cityDto) {
        Optional<CityDto> optionalCityDto = Optional.ofNullable(service.createByName(cityDto, "city", cityDto.getCity()));
        if (optionalCityDto.isPresent()) {
            return Response.ok(optionalCityDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create city").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, CityDto cityDto) {
        cityDto.setId(id);
//        Optional<CityDto> optionalCityDto = Optional.of(service.update(id, cityDto));
        boolean res = service.update(id,cityDto);
        if (res) {
            return Response.ok("City was updated successfully").build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("can't update this city").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        service.deleteById(id);
        return Response.ok("city was deleted successfully").build();
    }
}
