package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface ActorMapper extends BaseMapper<ActorDto, Actor> {
     ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);
}