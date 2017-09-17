package com.nwnx.rs.applications;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.nwnx.rs.providers.exception.*;
import com.nwnx.rs.providers.filters.CharsetResponseFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class NwnxApplication extends ResourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(NwnxApplication.class);

    public NwnxApplication() {
        logger.info("Registering JAX-RS resources");

        // scan all resource classes
        packages(true, "com.nwnx.rs.resources");

        // register exception mappers
        registerClasses(JsonGenerationExceptionMapper.class,
                JsonMappingExceptionMapper.class,
                JsonParseExceptionMapper.class,
                WebApplicationExceptionMapper.class,
                CatchAllExceptionMapper.class);

        // register filters
        registerClasses(CsrfProtectionFilter.class,
                CharsetResponseFilter.class);

        // register entity providers
        registerInstances(getJacksonJsonProvider());

        // register logging feature
        // TODO: make it work with slf4j
//        registerClasses(LoggingFeature.class);

        logger.info("Registered all JAX-RS resources");
    }

    private JacksonJsonProvider getJacksonJsonProvider() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                .configure(SerializationFeature.INDENT_OUTPUT, true);
        return new JacksonJsonProvider(mapper);
    }
}