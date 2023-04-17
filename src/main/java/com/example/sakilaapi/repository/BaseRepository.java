package com.example.sakilaapi.repository;

import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
        return Optional.ofNullable(entity);
    }

    public T save(T entity) {
        return Database.doInTransaction(em -> {
            em.persist(entity);
            return entity;
        });
    }
    public boolean isExist(ID id){
        return Database.doInTransaction(
                entityManager -> {
                    T entity = entityManager.find(entityType,id);
                    if (entity == null)
                        return false;
                    else
                        return true;
                }
        );
    }
    public List<T> getAll(EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityType);
        Root<T> root = query.from(entityType);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

   public T update(T entity) {
        return Database.doInTransaction(em -> em.merge(entity));
    }

    public void delete(T entity) {
        Database.doInTransactionWithoutResult(em -> em.remove(entity));
    }
    public void deleteById(ID id) {
        Database.doInTransactionWithoutResult(em -> {
            T entity = em.find(entityType, id);
            if (entity != null) {
                em.remove(entity);
            }else
                throw new EntityNotFoundException("There is no "+entityType.getSimpleName()+" with this id");
        });
    }
}
