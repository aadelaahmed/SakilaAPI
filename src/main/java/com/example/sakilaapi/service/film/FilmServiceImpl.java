package com.example.sakilaapi.service.film;

import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.model.Film;
import com.example.sakilaapi.repository.FilmRepository;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

public class FilmServiceImpl implements FilmService{
    private FilmRepository filmRepository;
    private FilmMapper filmMapper;
    public FilmServiceImpl() {
        filmMapper = FilmMapper.INSTANCE;
        filmRepository = new FilmRepository();
    }

    @Override
    public List<FilmDto> getAllFilms() {
        return null;
    }

    @Override
    public FilmDto getFilmById(Short id) {
        Optional<Film> optionalFilm = filmRepository.getById(id);
        if (!optionalFilm.isPresent()){
            return filmMapper.toDto(optionalFilm.get());
        }
        throw new NotFoundException("There is no film with this id");
    }

    @Override
    public List<FilmDto> searchFilms(Integer releaseYear, List<Integer> categoryIds) {
        return filmMapper.toDto(filmRepository.searchFilms(releaseYear, categoryIds));
    }

    @Override
    public List<FilmDto> getFilmsByActor(Short actorId) {
        return filmMapper.toDto(filmRepository.findFilmsByActorId(actorId));
    }

    @Override
    public List<FilmDto> getAvailableFilms() {
        return filmMapper.toDto(filmRepository.getAll());
    }

    @Override
    public FilmDto saveFilm(FilmDto filmDto) {
        return filmMapper.toDto(filmRepository.save(filmMapper.toEntity(filmDto)));
    }

    @Override
    public FilmDto updateFilm(Short id,FilmDto filmDto) {
        Optional<Film> optionalFilm = filmRepository.getById(id);
        if (!optionalFilm.isPresent())
            throw new NotFoundException("Can't get the film with this id");
        else {
            Film film = optionalFilm.get();
            film.setRating(filmDto.getRating());
            return filmMapper.toDto(filmRepository.save(film));
        }
    }

    @Override
    public void deleteFilm(FilmDto filmDto) {
        filmRepository.delete(filmMapper.toEntity(filmDto));
    }

    @Override
    public void deleteFilmById(Short id) {
        filmRepository.deleteById(id);
    }
}
