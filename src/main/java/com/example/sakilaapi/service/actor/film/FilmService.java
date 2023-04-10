package com.example.sakilaapi.service.actor.film;

public interface FilmService {
    List<FilmDto> getAllFilms();
    FilmDto getFilmById(Long id);
    List<FilmDto> searchFilms(String keyword);
    List<FilmDto> getFilmsByActor(Long actorId);
    List<FilmDto> getAvailableFilms();
}
