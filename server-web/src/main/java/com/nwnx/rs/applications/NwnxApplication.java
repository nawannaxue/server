package com.nwnx.rs.applications;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.nwnx.rs.providers.exception.CatchAllExceptionMapper;
import com.nwnx.rs.providers.exception.JsonGenerationExceptionMapper;
import com.nwnx.rs.providers.exception.JsonMappingExceptionMapper;
import com.nwnx.rs.providers.exception.JsonParseExceptionMapper;
import com.nwnx.rs.providers.exception.WebApplicationExceptionMapper;
import com.nwnx.rs.providers.filters.CharsetResponseFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Level;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

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
        setupJulToSlf4j();
        property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, Level.INFO.getName());

        logger.info("Registered all JAX-RS resources");
    }

    private void setupJulToSlf4j() {
        // Optionally remove existing handlers attached to j.u.l root logger
        SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)

        // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
        // the initialization phase of your application
        SLF4JBridgeHandler.install();
    }

    private JacksonJsonProvider getJacksonJsonProvider() {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
//                .setVisibility(FIELD, ANY)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                .configure(SerializationFeature.INDENT_OUTPUT, true);
        return new JacksonJsonProvider(mapper);
    }
}