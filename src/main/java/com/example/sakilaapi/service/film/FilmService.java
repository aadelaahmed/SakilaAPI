package com.example.sakilaapi.service.film;

import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<FilmDto> getAllFilms();
    FilmDto getFilmById(Short id);
    List<FilmDto> searchFilms(Integer releaseYear, List<Integer> categoryIds);
    List<FilmDto> getFilmsByActor(Short actorId);
    List<FilmDto> getAvailableFilms();
    FilmDto saveFilm(FilmDto filmDto);

    FilmDto updateFilm(Short id,FilmDto filmDto);

    void deleteFilm(FilmDto filmDto);

    void deleteFilmById(Short id);
}

