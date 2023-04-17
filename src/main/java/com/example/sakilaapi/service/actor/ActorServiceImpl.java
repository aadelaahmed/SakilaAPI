package com.example.sakilaapi.service.actor;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.repository.BaseRepository;
import com.example.sakilaapi.service.BaseService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ActorServiceImpl extends BaseService<Actor,ActorDto> {
    ActorRepository actorRepository;
    ActorMapper actorMapper;
    public ActorServiceImpl(ActorRepository actorRepository, ActorMapper actorMapper) {
        super(actorRepository, actorMapper);
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }


    @Override
    protected Class<Actor> getEntityClass() {
        return Actor.class;
    }

    @Override
    protected Class<ActorDto> getDtoClass() {
        return ActorDto.class;
    }

}
