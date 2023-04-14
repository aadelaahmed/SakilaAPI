package com.example.sakilaapi.service.actor;

import com.example.sakilaapi.dto.ActorDto;

import java.util.List;

public interface ActorService {
        List<ActorDto> getAllActors();
        ActorDto getActorById(Integer id);
        ActorDto createActor(ActorDto actor);
        void deleteActor(Integer id);
        ActorDto updateActor(Integer id, ActorDto actorDetails);
}
