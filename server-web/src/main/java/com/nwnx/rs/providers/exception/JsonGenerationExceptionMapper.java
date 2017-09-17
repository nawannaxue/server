package com.nwnx.rs.providers.exception;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.nwnx.rs.dto.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonGenerationExceptionMapper implements ExceptionMapper<JsonGenerationException> {
    private static final Logger logger = LoggerFactory.getLogger(JsonGenerationExceptionMapper.class);

    @Override
    public Response toResponse(JsonGenerationException exception) {
        logger.info("Problem writing JSON", exception);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorResponse.of(Response.Status.BAD_REQUEST.getStatusCode(), "Problem writing JSON", exception))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}