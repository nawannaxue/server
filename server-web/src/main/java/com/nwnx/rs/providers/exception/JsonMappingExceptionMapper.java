package com.nwnx.rs.providers.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.nwnx.rs.dto.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
    private static final Logger logger = LoggerFactory.getLogger(JsonMappingExceptionMapper.class);

    @Override
    public Response toResponse(JsonMappingException exception) {
        logger.info("Problem parsing JSON", exception);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorResponse.of(Response.Status.BAD_REQUEST.getStatusCode(), "Problem parsing JSON", exception))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}