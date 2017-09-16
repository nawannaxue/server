package com.nwnx.components.validation.impl;

import com.nwnx.components.validation.BeanValidator;
import com.nwnx.rs.dto.responses.ViolationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BeanValidatorImpl implements BeanValidator {
    private static final Logger logger = LoggerFactory.getLogger(BeanValidatorImpl.class);

    private Validator validator;

    @PostConstruct
    public void init() {
        logger.info("Initializing bean validator");
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> void validate(T object, Class<?>... groups) {
        List<ViolationResponse> violationResponses = validator.validate(object, groups)
                                                .stream()
                                                .map(ViolationResponse::from)
                                                .collect(Collectors.toList());

        if (!violationResponses.isEmpty()) {
            throw new ClientErrorException(Response.status(422).entity(violationResponses).build());
        }
    }
}
