package com.example.sakilaapi.controller.api;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.service.actor.ActorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Path("/actors")
public class ActorController {
    //TODO -> USE IOC spring container HERE
    private final ActorService service = new ActorService(
            new ActorRepository(), ActorMapper.INSTANCE
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActors() {
        List<ActorDto> actorDtos = service.getAll();
        System.out.println(actorDtos.stream().limit(3));
        GenericEntity entity = new GenericEntity<>(actorDtos) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorBy(@PathParam("id") Integer id) {
        Optional<ActorDto> optionalActorDto = Optional.ofNullable(service.getById(id));
        return Response.ok().entity(
                optionalActorDto.get()
        ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActor(ActorDto actorDto) {
        Optional<ActorDto> optionalActorDto = Optional.ofNullable(service.createByName(actorDto,"firstName",actorDto.getFirstName()));
        if (optionalActorDto.isPresent()){
            return Response.ok(optionalActorDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create actor").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateActor(@PathParam("id") Integer id, ActorDto actorDto) {
        actorDto.setId(id);
        actorDto.setLastUpdate(Instant.now());
        ActorDto res = service.update(id,actorDto);
        if (res != null) {
            return Response.ok(res).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("can't update this actor").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteActorById(@PathParam("id") Integer id) {
        service.deleteById(id);
        return Response.ok("Actor's deleted successfully").build();
    }
}
