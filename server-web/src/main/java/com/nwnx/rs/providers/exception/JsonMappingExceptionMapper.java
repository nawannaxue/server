package com.nwnx.rs.providers.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nwnx.rs.dto.responses.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

    @Override
    public Response toResponse(JsonMappingException exception) {
        return Response.serverError()
                .entity(ErrorResponse.of(4000, "Problem parsing JSON", exception))
                .build();
    }
}