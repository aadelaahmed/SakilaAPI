package com.example.sakilaapi.repository;

import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T extends Serializable,ID> {
    private final Class<T> entityType;
    public BaseRepository(Class<T> entityClass) {
        this.entityType = entityClass;
    }

    public Optional<T> getById(ID id) {
        T entity = Database.doInTransaction(em -> em.find(entityType, id));
        return Optional.ofNullable(entity);    }

    public List<T> getAll() {
        return Database.doInTransaction(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(entityType);
            Root<T> root = query.from(entityType);
            query.select(root);
            return em.createQuery(query).getResultList();
        });
    }

    public T save(T entity) {
        return Database.doInTransaction(em -> {
            em.persist(entity);
            return entity;
        });
    }

    public void update(T entity) {
        Database.doInTransactionWithoutResult(em -> em.merge(entity));
    }

    public void delete(T entity) {
        Database.doInTransactionWithoutResult(em -> em.remove(entity));
    }
    public void deleteById(ID id) {
        Database.doInTransactionWithoutResult(em -> {
            T entity = em.find(entityType, id);
            if (entity != null) {
                em.remove(entity);
            }
        });
    }
}
