package com.example.sakilaapi.service.film;

import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.model.Film;
import com.example.sakilaapi.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository;
    private FilmMapper filmMapper;
    private static final Logger logger = LogManager.getLogManager().getLogger("FilmServiceImpl");


    public FilmServiceImpl() {
        filmMapper = FilmMapper.INSTANCE;
        filmRepository = new FilmRepository();
    }

    @Override
    public List<FilmDto> getAllFilms() {
        return filmMapper.toDto(filmRepository.getAll());
    }

    @Override
    public FilmDto getFilmById(Integer id) {
        Optional<Film> optionalFilm = filmRepository.getById(id);
        if (!optionalFilm.isPresent()) {
            throw new EntityNotFoundException("Can't fetch film with id: " + id);
        } else {
            System.out.println("get the film object successfully in film service -> " + optionalFilm.get());
            return filmMapper.toDto(optionalFilm.get());

        }

    }

    @Override
    public List<FilmDto> searchFilms(Integer releaseYear, List<Integer> categoryIds) {
        return filmMapper.toDto(filmRepository.searchFilms(releaseYear, categoryIds));
    }

    @Override
    public List<FilmDto> getFilmsByActor(Integer actorId) {
        return filmMapper.toDto(filmRepository.findFilmsByActorId(actorId));
    }

    @Override
    public List<FilmDto> getAvailableFilms() {
        return filmMapper.toDto(filmRepository.getAll());
    }

    @Override
    public FilmDto saveFilm(FilmDto filmDto) {
        //check first if there is no film with this title.
        Film checkExistedFilm = filmRepository.searchFilmByTitle(filmDto.getTitle());
        if (checkExistedFilm != null) {
            throw new EntityAlreadyExistException("Film already exists");
        } else {
            Film film = filmMapper.toEntity(filmDto);
            film.setId(null);
            System.out.println("film obj which is saved in db -> " + film.toString());
            return filmMapper.toDto(filmRepository.save(film));
        }
    }

    @Override
    public FilmDto updateFilm(Integer id, FilmDto filmDto) {
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
    public void deleteFilmById(Integer id) {
        filmRepository.deleteById(id);
    }
}
