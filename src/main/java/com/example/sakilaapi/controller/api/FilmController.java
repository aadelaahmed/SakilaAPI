package com.example.sakilaapi.controller.api;

import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.repository.FilmRepository;
import com.example.sakilaapi.service.film.FilmService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/films")
public class FilmController {
    //TODO -> USE IOC spring container HERE

    private final FilmService service = new FilmService(
            new FilmRepository(),
            FilmMapper.INSTANCE
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFilms() {
        List<FilmDto> filmDtos = service.getAll();
        GenericEntity<List<FilmDto>> entity = new GenericEntity<>(filmDtos) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilm(@PathParam("id") Integer id) {
        Optional<FilmDto> optionalFilmDto = Optional.ofNullable(service.getById(id));
        return Response.ok().entity(
                optionalFilmDto.get()
        ).build();
    }


    @GET
    @Path("/actors/{actorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmByActor(@PathParam("actorId") Integer actorId) {
        List<FilmDto> filmDtos = service.getFilmsByActor(actorId);
        GenericEntity<List<FilmDto>> entity = new GenericEntity<List<FilmDto>>(filmDtos) {
        };

        return Response.ok(entity).build();
    }

    @GET
    @Path("/categories/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmByCategory(@PathParam("categoryId") Integer categoryId) {
        List<FilmDto> filmDtos = service.getFilmsByCategoryId(categoryId);
        GenericEntity<List<FilmDto>> entity = new GenericEntity<List<FilmDto>>(filmDtos) {
        };
        return Response.ok(entity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFilm(FilmDto filmDto) {
        boolean isExisted = service.isExistFilmByTitle(filmDto.getTitle());
        if (isExisted) {
            return Response.status(Response.Status.BAD_REQUEST).entity("The film is already existed").build();
        } else {
            filmDto.setId(null);
            Optional<FilmDto> optionalFilmDto = Optional.ofNullable(service.createByName(filmDto,"title", filmDto.getTitle()));
            if (optionalFilmDto.isPresent()) {
                return Response.ok(optionalFilmDto.get()).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity("Can't create this film").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFilm(@PathParam("id") Integer id, FilmDto filmDto) {
        filmDto.setId(id);
        filmDto.setLastUpdate(Instant.now());
        FilmDto res = service.update(id, filmDto);
        if (res != null) {
            return Response.ok(res).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("can't update this Film").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFilm(@PathParam("id") Integer id) {
        service.deleteById(id);
        return Response.ok("the film was deleted successfully").build();
    }
}

