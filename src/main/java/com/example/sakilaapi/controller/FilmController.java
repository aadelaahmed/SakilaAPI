package com.example.sakilaapi.controller;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.dto.FilmDto;
import com.example.sakilaapi.service.film.FilmServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/films")
public class FilmController {
    private final FilmServiceImpl service = new FilmServiceImpl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<FilmDto> filmDtos = service.getAllFilms();
        GenericEntity<List<FilmDto>> entity = new GenericEntity<>(filmDtos) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Optional<FilmDto> optionalFilmDto = Optional.ofNullable(service.getFilmById(id));
        return Response.ok().entity(
                optionalFilmDto.get()
        ).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("releaseYear") Integer releaseYear,
                           @QueryParam("categoryIds") List<Integer> categoryIds) {
        List<FilmDto> filmDtos = service.searchFilms(releaseYear, categoryIds);
        GenericEntity<List<FilmDto>> entity = new GenericEntity<List<FilmDto>>(filmDtos) {
        };
        return Response.ok(entity).build();
    }

    @GET
    @Path("/actors/{actorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByActor(@PathParam("actorId") Integer actorId) {
        List<FilmDto> filmDtos = service.getFilmsByActor(actorId);
        GenericEntity<List<FilmDto>> entity = new GenericEntity<List<FilmDto>>(filmDtos) {
        };

        return Response.ok(entity).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(FilmDto filmDto) {
        Optional<FilmDto> optionalFilmDto = Optional.ofNullable(service.saveFilm(filmDto));
        if (optionalFilmDto.isPresent()) {
            return Response.ok(optionalFilmDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create this film").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, FilmDto filmDto) {
        filmDto.setId(id);
        Optional<FilmDto> optionalFilmDto = Optional.of(service.getFilmById(id));
        if (optionalFilmDto.isPresent()) {
            return Response.ok(optionalFilmDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("can't update this film").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        service.deleteFilmById(id);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(FilmDto filmDto) {
        service.deleteFilm(filmDto);
        return Response.ok().build();
    }
}

