package com.example.sakilaapi.service.actor;

import com.example.sakilaapi.dto.ActorDTO;
import com.example.sakilaapi.exception.ResourceNotFoundException;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.repository.ActorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorService {

    private ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<ActorDTO> getAllActors() {
        return ActorMapper.INSTANCE.toActorDTOList(actorRepository.getAll());
    }

    public Actor getActorById(Short id) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (optionalActor.isPresent()) {
            return optionalActor.get();
        } else {
            throw new ResourceNotFoundException("Actor", "id", id);
        }
    }

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(Short id) {
        if (!actorRepository.getById(id).isPresent()) {
            throw new ResourceNotFoundException("Actor", "id", id);
        }
        actorRepository.deleteById(id);
    }

    public Actor updateActor(Short id, Actor actorDetails) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (optionalActor.isPresent()) {
            Actor actor = optionalActor.get();
            actor.setFirstName(actorDetails.getFirstName());
            actor.setLastName(actorDetails.getLastName());
            return actorRepository.save(actor);
        } else {
            //TODO -> search for handling exceptions in appropriate way.
            throw new ResourceNotFoundException("Actor", "id", id);
        }
    }


}
