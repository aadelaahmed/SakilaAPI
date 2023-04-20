package com.example.sakilaapi.service.actor;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.service.BaseService;

public class ActorService extends BaseService<Actor,ActorDto> {
    ActorRepository actorRepository;
    ActorMapper actorMapper;
    public ActorService(ActorRepository actorRepository, ActorMapper actorMapper) {
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
