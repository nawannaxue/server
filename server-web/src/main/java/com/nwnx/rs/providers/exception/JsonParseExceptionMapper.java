package com.nwnx.rs.providers.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.nwnx.rs.dto.responses.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

    @Override
    public Response toResponse(JsonParseException exception) {
        return Response.serverError()
                .entity(ErrorResponse.of(4000, "Body should be a JSON object", exception))
                .build();
    }
}