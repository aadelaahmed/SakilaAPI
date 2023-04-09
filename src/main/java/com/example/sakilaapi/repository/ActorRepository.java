package com.example.sakilaapi.repository;


import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ActorRepository extends BaseRepository<Actor, Short> {
    public ActorRepository() {
        super(Actor.class);
    }

    public List<Actor> findByLastName(String lastName) {
        return Database.doInTransaction(
               entityManager -> {
                   CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                   CriteriaQuery<Actor> query = criteriaBuilder.createQuery(Actor.class);
                   Root<Actor> root = query.from(Actor.class);
                   query.select(root).where(criteriaBuilder.equal(root.get("lastName"), lastName));
                   TypedQuery<Actor> typedQuery = entityManager.createQuery(query);
                   return typedQuery.getResultList();
               }
        );
    }
}

