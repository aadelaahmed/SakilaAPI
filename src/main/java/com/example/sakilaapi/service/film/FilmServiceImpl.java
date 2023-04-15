package com.example.sakilaapi.service.film;

import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.mapper.LanguageMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.model.Category;
import com.example.sakilaapi.model.Film;
import com.example.sakilaapi.model.Language;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.repository.CategoryRepository;
import com.example.sakilaapi.repository.FilmRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository;
    private ActorRepository actorRepository;
    private FilmMapper filmMapper;
    private CategoryRepository categoryRepository;
    private static final Logger logger = LogManager.getLogManager().getLogger("FilmServiceImpl");
    private LanguageMapper languageMapper;

    public FilmServiceImpl() {
        filmMapper = FilmMapper.INSTANCE;
        languageMapper = LanguageMapper.INSTANCE;
        filmRepository = new FilmRepository();
        this.actorRepository = new ActorRepository();
        this.categoryRepository = new CategoryRepository();
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

    /*@Override
    public List<FilmDto> searchFilms(Integer releaseYear, List<Integer> categoryIds) {
        return filmMapper.toDto(filmRepository.searchFilms(releaseYear, categoryIds));
    }*/

    @Override
    public List<FilmDto> getFilmsByActor(Integer actorId) {
        Optional<Actor> optionalActor = actorRepository.getById(actorId);
        if (!optionalActor.isPresent())
            throw new EntityNotFoundException("There is no actor with id: " + actorId);
        else {
            List<Film> films = filmRepository.findFilmsByActorId(actorId);
            return filmMapper.toDto(films);
        }
    }

    @Override
    public List<FilmDto> getFilmsByCategoryId(Integer categoryId) {
        Optional<Category> optionalCategory = categoryRepository.getById(categoryId);
        if (!optionalCategory.isPresent())
            throw new EntityNotFoundException("There is no category with id: " + categoryId);
        else {
            List<Film> films = filmRepository.findFilmsByCategoryId(categoryId);
            return filmMapper.toDto(films);
        }
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
            throw new EntityNotFoundException("Can't get the film with this id");
        else {
            /*Film film = optionalFilm.get();
            film.setRating(filmDto.getRating());*/
            Film oldFilm = optionalFilm.get();
            Film newUpdatedFilm = cutsomMapperForUpdatingFilm(oldFilm, filmDto);
            System.out.println("Start update the film");
            System.out.println("before merging the new updated film ->" + newUpdatedFilm.toString());
            return filmMapper.toDto(filmRepository.update(newUpdatedFilm));
        }
    }

    private Film cutsomMapperForUpdatingFilm(Film oldFilm, FilmDto filmDto) {
        Film updatedFilm = new Film();
        updatedFilm.setId(oldFilm.getId());
        updatedFilm.setTitle(filmDto.getTitle() != null ? filmDto.getTitle() : oldFilm.getTitle());
        updatedFilm.setDescription(filmDto.getDescription() != null ? filmDto.getDescription() : oldFilm.getDescription());
        updatedFilm.setReleaseYear(filmDto.getReleaseYear() != null ? filmDto.getReleaseYear() : oldFilm.getReleaseYear());
        updatedFilm.setRentalDuration(filmDto.getRentalDuration() != null ? filmDto.getRentalDuration() : oldFilm.getRentalDuration());
        updatedFilm.setRentalRate(filmDto.getRentalRate() != null ? filmDto.getRentalRate() : oldFilm.getRentalRate());
        updatedFilm.setReplacementCost(filmDto.getReplacementCost() != null ? filmDto.getReplacementCost() : oldFilm.getReplacementCost());
        updatedFilm.setLength(filmDto.getLength() != null ? filmDto.getLength() : oldFilm.getLength());
        updatedFilm.setRating(filmDto.getRating() != null ? filmDto.getRating() : oldFilm.getRating());
        System.out.println("start with updating language here in film service");
        System.out.println("test language of film dto ->" + filmDto.getLanguage());
        System.out.println("test language of film entity ->" + oldFilm.getLanguage());
        updatedFilm.setLanguage(filmDto.getLanguage() != null ? languageMapper.toEntity(filmDto.getLanguage()) : oldFilm.getLanguage());
//        updatedFilm.setOriginalLanguage(filmDto.getOriginalLanguage() != null ? languageRepository.getById(filmDto.getOriginalLanguage().getId()).get() : oldFilm.getOriginalLanguage());
        updatedFilm.setOriginalLanguage(filmDto.getOriginalLanguage() != null ? languageMapper.toEntity(filmDto.getOriginalLanguage()) : oldFilm.getOriginalLanguage());
        updatedFilm.setLastUpdate(Instant.now());
        return updatedFilm;
    }


    @Override
    public void deleteFilmById(Integer id) {
        filmRepository.deleteById(id);
    }
}
