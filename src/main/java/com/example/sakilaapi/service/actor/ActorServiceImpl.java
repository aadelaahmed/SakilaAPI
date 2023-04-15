package com.example.sakilaapi.service.actor;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.repository.ActorRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ActorServiceImpl implements ActorService {
    private ActorRepository actorRepository;
    private ActorMapper actorMapper;
    private static final Logger logger = LogManager.getLogManager().getLogger("ActorServiceImpl");

    public ActorServiceImpl() {
        this.actorRepository = new ActorRepository();
        this.actorMapper = ActorMapper.INSTANCE;
    }

    @Override
    public ActorDto updateActor(Integer id, ActorDto actorDetails) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (!optionalActor.isPresent())
            throw new EntityNotFoundException("Can't get the actor with id: " + id);
        else {
            Actor actor = optionalActor.get();
            actor.setFirstName(actorDetails.getFirstName());
            actor.setLastName(actorDetails.getLastName());
            return actorMapper.toDto(actorRepository.update(actor));
        }
    }

    @Override
    public List<ActorDto> getAllActors() {
        return actorMapper.toDto(actorRepository.getAll());
    }

    @Override
    public ActorDto getActorById(Integer id) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (!optionalActor.isPresent())
            throw new EntityNotFoundException("Can't fetch actor with id: " + id);
        else {
            System.out.println("get the actor object successfully in actor service -> " + optionalActor.get());
            logger.info("get the actor object successfully in actor service -> " + optionalActor.get());
            return actorMapper.toDto(optionalActor.get());
        }
    }

    @Override
    public ActorDto createActor(ActorDto actorDto) {
        //firstly,ensures that there is no actor with this id in db.
        Optional<Actor> optionalExistedActor = null;
        if (actorDto.getId() != null) {
            optionalExistedActor = actorRepository.getById(actorDto.getId());
            if (optionalExistedActor.isPresent())
                throw new EntityAlreadyExistException("Actor already exists with id: " + actorDto.getId());
        }
        Actor actor = actorMapper.toEntity(actorDto);
        actor.setId(null);
        System.out.println("actor obj which is saved in db -> " + actor.toString());
        return actorMapper.toDto(actorRepository.save(actor));
    }

    @Override
    public void deleteActor(Integer id) {
        Optional<Actor> optionalActor = actorRepository.getById(id);
        if (!optionalActor.isPresent()) {
            throw new EntityNotFoundException("can't get this actor with id: " + id);
        }
        actorRepository.deleteById(id);
    }

}
