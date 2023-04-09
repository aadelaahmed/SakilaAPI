package com.example.sakilaapi.dto;

import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
    private Short actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
    private Set filmActors = new HashSet(0);
}
