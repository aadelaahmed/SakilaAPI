package com.example.sakilaapi.service.actor;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.repository.ActorRepository;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

public class ActorService {

    private ActorRepository actorRepository;
    private ActorMapper actorMapper;
    public ActorService() {
        this.actorRepository = new ActorRepository();
        this.actorMapper = Mappers.getMapper(ActorMapper.class);
    }

    public List<ActorDto> getAllActors() {
        return actorMapper.toDto(actorRepository.getAll());
    }

    public Response getActorById(Short id) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (optionalActor.isPresent()) {
            return Response.ok(optionalActor.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(
                    "There is no actor with this id"
            ).build();
        }
    }

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(Short id) {
        if (!actorRepository.getById(id).isPresent()) {
            throw new NotFoundException("can't get this actor with this id");
        }
        actorRepository.deleteById(id);
    }

    public Actor updateActor(Short id, Actor actorDetails) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (!optionalActor.isPresent())
            throw new NotFoundException("Can't get the actor with this id");
        else{
            Actor actor = optionalActor.get();
            actor.setFirstName(actorDetails.getFirstName());
            actor.setLastName(actorDetails.getLastName());
            return actorRepository.save(actor);
        }
    }


}
