package com.example.sakilaapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FilmActorId implements Serializable {
    private static final long serialVersionUID = 2342570498337513093L;
    @Column(name = "actor_id", columnDefinition = "SMALLINT UNSIGNED not null")
    private Integer actorId;

    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED not null")
    private Integer filmId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FilmActorId entity = (FilmActorId) o;
        return Objects.equals(this.actorId, entity.actorId) &&
                Objects.equals(this.filmId, entity.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId);
    }

}