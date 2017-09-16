package com.nwnx.rs.providers.exception;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.nwnx.rs.dto.responses.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonGenerationExceptionMapper implements ExceptionMapper<JsonGenerationException> {

    @Override
    public Response toResponse(JsonGenerationException exception) {
        return Response.serverError()
                .entity(ErrorResponse.of(4000, "Problem writing JSON", exception))
                .build();
    }
}