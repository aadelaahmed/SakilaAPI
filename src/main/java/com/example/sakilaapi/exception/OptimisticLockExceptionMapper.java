package com.example.sakilaapi.exception;

import jakarta.persistence.OptimisticLockException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class OptimisticLockExceptionMapper implements ExceptionMapper<OptimisticLockException> {
    /*
    thrown when a transaction attempts to update or delete an entity that has been modified by another transaction
     since the first transaction read it.
     */
    @Override
    public Response toResponse(OptimisticLockException ex) {
        return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build();
    }
}
