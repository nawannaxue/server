package com.nwnx.rs.providers.exception;

import com.nwnx.rs.dto.responses.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        return Response.serverError()
                .entity(ErrorResponse.of(5000, "Internal server error and please contact your administrator", exception))
                .build();
    }
}
