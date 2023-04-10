package com.example.sakilaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * A DTO for the {@link com.example.sakilaapi.model.Actor} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDto implements Serializable {
    private Short actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
    private Set filmActors;
}