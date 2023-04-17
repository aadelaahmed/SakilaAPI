package com.example.sakilaapi.controller.api;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.service.actor.ActorServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/addresses")
public class AddressController {
    //TODO -> USE IOC spring container HERE
    private final ActorServiceImpl service = new ActorServiceImpl(
            new ActorRepository(), ActorMapper.INSTANCE
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<ActorDto> actorDtos = service.getAll();
        System.out.println(actorDtos.stream().limit(3));
        GenericEntity entity = new GenericEntity<>(actorDtos) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Optional<ActorDto> optionalActorDto = Optional.ofNullable(service.getById(id));
        return Response.ok().entity(
                optionalActorDto.get()
        ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ActorDto actorDto) {
        Optional<ActorDto> optionalActorDto = Optional.ofNullable(service.create(actorDto,actorDto.getId()));
        if (optionalActorDto.isPresent()){
            return Response.ok(optionalActorDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create actor").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, ActorDto actorDto) {
        actorDto.setId(id);
        Optional<ActorDto> optionalActorDto = Optional.of(service.update(id,actorDto));
        if (optionalActorDto.isPresent()){
            return Response.ok(optionalActorDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("can't update this actor").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        service.deleteById(id);
        return Response.ok("Actor's deleted successfully").build();
    }
}
