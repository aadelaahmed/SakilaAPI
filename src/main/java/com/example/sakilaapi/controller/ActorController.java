package com.example.sakilaapi.controller;

import com.example.sakilaapi.dto.ActorDTO;
import com.example.sakilaapi.exception.ResourceNotFoundException;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.service.actor.ActorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;
import java.util.Optional;

@Path("/actors")
public class ActorController {
    private final ActorService service = new ActorService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActorDTO> getAll() {
        return service.getAllActors();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Short id) {
        return service.getActorById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Actor create(Actor actor) {
        return service.createActor(actor);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Actor update(@PathParam("id") Short id, Actor actor) {
        actor.setActorId(id);
        return service.updateActor(id,actor);
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Short id) {
        service.deleteActor(id);
    }
}
