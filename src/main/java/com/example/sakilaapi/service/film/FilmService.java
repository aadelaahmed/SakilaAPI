package com.example.sakilaapi.service.film;

import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.model.Category;
import com.example.sakilaapi.model.Film;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.repository.CategoryRepository;
import com.example.sakilaapi.repository.FilmRepository;
import com.example.sakilaapi.service.BaseService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class FilmService extends BaseService<Film,FilmDto> {
    FilmRepository filmRepository;
    FilmMapper filmMapper;
    ActorRepository actorRepository;
    CategoryRepository categoryRepository;
    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        super(filmRepository, filmMapper);
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
        this.actorRepository = new ActorRepository();
        this.categoryRepository = new CategoryRepository();
    }

    @Override
    protected Class<Film> getEntityClass() {
        return Film.class;
    }

    @Override
    protected Class<FilmDto> getDtoClass() {
        return FilmDto.class;
    }

    public List<FilmDto> getFilmsByActor(Integer actorId) {
        Optional<Actor> optionalActor = actorRepository.getById(actorId);
        if (!optionalActor.isPresent())
            throw new EntityNotFoundException("There is no actor with id: " + actorId);
        else {
            List<Film> films = filmRepository.findFilmsByActorId(actorId);
            return filmMapper.toDto(films);
        }
    }

    public List<FilmDto> getFilmsByCategoryId(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.getById(categoryId);
        if (!optionalCategory.isPresent())
            throw new EntityNotFoundException("There is no category with id: " + categoryId);
        else {
            List<Film> films = filmRepository.findFilmsByCategoryId(categoryId);
            return filmMapper.toDto(films);
        }
    }

    public boolean isExistFilmByTitle(String title) {
        Film film = filmRepository.searchFilmByTitle(title);
        if (film != null)
            return true;
        else
            return false;
    }

}
