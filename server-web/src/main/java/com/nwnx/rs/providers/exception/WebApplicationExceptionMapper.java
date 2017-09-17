package com.nwnx.rs.providers.exception;

import com.nwnx.rs.dto.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);

    @Override
    public Response toResponse(WebApplicationException exception) {
        logger.info("Caught web application exception", exception);

        Response er = exception.getResponse();
        if (!er.hasEntity()) {
            return Response.fromResponse(er)
                    .entity(ErrorResponse.of(er.getStatus(), er.getStatusInfo().getReasonPhrase(), exception))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        throw exception;
    }
}
