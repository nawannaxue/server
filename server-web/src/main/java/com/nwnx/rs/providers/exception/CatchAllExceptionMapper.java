package com.nwnx.rs.providers.exception;

import com.nwnx.rs.dto.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger logger = LoggerFactory.getLogger(CatchAllExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        logger.info("Internal server error", exception);
        return Response.serverError()
                .entity(ErrorResponse.of(5000, "Internal server error and please contact your administrator", exception))
                .build();
    }
}
