package com.nwnx.rs.providers.exception;

import com.nwnx.rs.dto.responses.ErrorResponse;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException exception) {
        ErrorResponse error = new ErrorResponse(4000, "Bad Request - please check and resubmit your request", exception.toString());
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
