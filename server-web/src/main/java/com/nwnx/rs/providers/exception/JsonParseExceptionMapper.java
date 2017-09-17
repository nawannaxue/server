package com.nwnx.rs.providers.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.nwnx.rs.dto.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
    private static final Logger logger = LoggerFactory.getLogger(JsonParseExceptionMapper.class);

    @Override
    public Response toResponse(JsonParseException exception) {
        logger.info("Body should be a JSON object", exception);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ErrorResponse.of(Response.Status.BAD_REQUEST.getStatusCode(), "Body should be a JSON object", exception))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}