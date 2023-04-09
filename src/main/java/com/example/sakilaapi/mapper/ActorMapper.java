package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.ActorDTO;
import com.example.sakilaapi.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ActorMapper {
    ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);
    ActorDTO toActorDto(Actor actor);
    Actor toActorEntity(ActorDTO actorDTO);
    List<Actor> toActorList(List<ActorDTO> actorDTOList);
    List<ActorDTO> toActorDTOList(List<Actor> actorList);
}
