package com.example.sakilaapi.controller;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.service.actor.ActorServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jdk.javadoc.doclet.Reporter;


import java.util.List;
import java.util.Optional;

@Path("/actors")
public class ActorController {
    private final ActorServiceImpl service = new ActorServiceImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(service.getAllActors()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Short id) {
        Optional<ActorDto> optionalActorDto = Optional.ofNullable(service.getActorById(id));
        if (optionalActorDto.isPresent())
        {
            Response.ok().entity(
                    optionalActorDto.get()
            ).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("can't find actor with this id").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ActorDto actorDto) {
        Optional<ActorDto> optionalActorDto = Optional.ofNullable(service.createActor(actorDto));
        if (optionalActorDto.isPresent()){
            return Response.ok(optionalActorDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create actor").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Short id, ActorDto actorDto) {
        actorDto.setActorId(id);
        Optional<ActorDto> optionalActorDto = Optional.of(service.updateActor(id,actorDto));
        if (optionalActorDto.isPresent()){
            return Response.ok(optionalActorDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("can't update this actor").build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Short id) {
        service.deleteActor(id);
    }
}
