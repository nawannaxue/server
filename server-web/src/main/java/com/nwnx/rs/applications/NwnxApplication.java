package com.nwnx.rs.applications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.nwnx.rs.providers.exception.BadRequestExceptionMapper;
import com.nwnx.rs.providers.exception.CatchAllExceptionMapper;
import com.nwnx.rs.providers.filters.CharsetResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/users")
public class NwnxApplication extends ResourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(NwnxApplication.class);

    public NwnxApplication() {
        logger.info("Registering JAX-RS resources");

        packages(true, "com.nwnx.rs.resources");

        registerClasses(BadRequestExceptionMapper.class, CatchAllExceptionMapper.class);

        registerClasses(CsrfProtectionFilter.class, CharsetResponseFilter.class);

        registerInstances(getJacksonJsonProvider());

        logger.info("Registered all JAX-RS resources");
    }

    private JacksonJsonProvider getJacksonJsonProvider() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        return new JacksonJsonProvider(mapper);
    }
}