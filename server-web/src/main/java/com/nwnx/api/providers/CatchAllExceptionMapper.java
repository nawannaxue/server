package com.nwnx.api.providers;

import com.nwnx.api.dto.responses.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        ErrorResponse error = new ErrorResponse(5000, "error", exception.toString());
        return Response.serverError().entity(error).build();
    }
}
