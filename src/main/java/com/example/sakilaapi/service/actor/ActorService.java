package com.example.sakilaapi.service.actor;

import com.example.sakilaapi.dto.ActorDto;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ActorService {
        List<ActorDto> getAllActors();
        ActorDto getActorById(Short id);
        ActorDto createActor(ActorDto actor);
        void deleteActor(Short id);
        ActorDto updateActor(Short id, ActorDto actorDetails);
}
