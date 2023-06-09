package com.example.sakilaapi.service;

import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.repository.BaseRepository;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityNotFoundException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<E extends Serializable, D> {
    protected BaseRepository<E, Integer> repository;
    protected BaseMapper<D, E> mapper;

    public BaseService(BaseRepository<E, Integer> repository, BaseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<D> getAll() {
        return Database.doInTransaction(
                entityManager -> {
                    List<E> entities = repository.getAll(entityManager);
                    return mapper.toDto(entities);
                }
        );
        /*List<E> entities = repository.getAll(entitManager);
        return mapper.toDto(entities);*/
        /*return entities.stream()
                .map(e -> mapper.map(e, getDtoClass()))
                .collect(Collectors.toList());*/
    }

    public D getById(Integer id) {
        Optional<E> optionalEntity = repository.getById(id);
        if (optionalEntity.isPresent()) {
            return mapper.toDto(optionalEntity.get());
        } else {
            throw new EntityNotFoundException("Can't fetch " + getEntityClass().getSimpleName() + " with id: " + id);
        }
    }

    public D create(D dto, Integer id) {
        if (id != null && dto != null) {
            Optional<E> optionalEntity = repository.getById(id);
            if (optionalEntity.isPresent()) {
                throw new EntityAlreadyExistException(getEntityClass().getSimpleName() + " is already existed");
            }
        }else
            throw new EntityNotFoundException("Can't create this "+getEntityClass().getSimpleName());
        E entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public D createByName(D dto,String attributeName,String name){
        if (name != null && dto != null) {
            Optional<E> optionalEntity = repository.getByName(attributeName,name);
            if (optionalEntity.isPresent()) {
                throw new EntityAlreadyExistException(getEntityClass().getSimpleName() + " is already existed");
            }
        }else
            throw new EntityNotFoundException("Can't create this "+getEntityClass().getSimpleName());
        E entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    /*public D update(Integer id, D dto) {
        Optional<E> optionalEntity = repository.getById(id);
        if (optionalEntity.isPresent()) {
            E entity = optionalEntity.get();
            mapper.partialUpdate(entity, dto);
            return mapper.toDto(repository.update(entity));
        } else {
            throw new EntityNotFoundException("Can't get the entity with id: " + id);
        }
    }*/
    public D update(Integer id, D dto) {
        return Database.doInTransaction(
                entityManager -> {
                    Optional<E> optionalEntity = Optional.ofNullable(entityManager.find(getEntityClass(), id));
                    if (optionalEntity.isPresent()) {
                        E entity = optionalEntity.get();
                        mapper.partialUpdate(entity, dto);
                        return mapper.toDto(entityManager.merge(entity));
                    } else {
                        throw new EntityNotFoundException("Can't get "+getEntityClass().getSimpleName()+" with id: " + id);
                    }
                }
        );
    }

    protected abstract Class<E> getEntityClass();

    protected abstract Class<D> getDtoClass();
}

