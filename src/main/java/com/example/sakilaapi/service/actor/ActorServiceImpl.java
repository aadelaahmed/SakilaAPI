package com.example.sakilaapi.service.actor;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.repository.ActorRepository;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

public class ActorServiceImpl implements ActorService {
    private ActorRepository actorRepository;
    private ActorMapper actorMapper;

    public ActorServiceImpl() {
        this.actorRepository = new ActorRepository();
        this.actorMapper = ActorMapper.INSTANCE;
    }

    @Override
    public ActorDto updateActor(Integer id, ActorDto actorDetails) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (!optionalActor.isPresent())
            throw new NotFoundException("Can't get the actor with this id");
        else {
            Actor actor = optionalActor.get();
            actor.setFirstName(actorDetails.getFirstName());
            actor.setLastName(actorDetails.getLastName());
            return actorMapper.toDto(actorRepository.save(actor));
        }
    }

    @Override
    public List<ActorDto> getAllActors() {
        return actorMapper.toDto(actorRepository.getAll());
    }

    @Override
    public ActorDto getActorById(Integer id) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (optionalActor.isPresent()) {
            return actorMapper.toDto(optionalActor.get());
            //return Response.ok(optionalActor.get()).build();
        } else {
            throw new NotFoundException("There is no actor with this id");
            /*return Response.status(Response.Status.NOT_FOUND).entity(
                    "There is no actor with this id"
            ).build();*/
        }
    }

    @Override
    public ActorDto createActor(ActorDto actorDto) {
        return actorMapper.toDto(actorRepository.save(actorMapper.toEntity(actorDto)));
    }

    @Override
    public void deleteActor(Integer id) {
        if (!actorRepository.getById(id).isPresent()) {
            throw new NotFoundException("can't get this actor with this id");
        }
        actorRepository.deleteById(id);
    }

}
