package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.mapper.EntityMapper;
import com.example.sakilaapi.model.Actor;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface ActorMapper extends EntityMapper<ActorDto,Actor> {

}