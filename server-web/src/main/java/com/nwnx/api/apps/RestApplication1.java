package com.nwnx.api.apps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/admin")
public class RestApplication1 extends ResourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(RestApplication1.class);

    public RestApplication1() {
        logger.info("Starting registering REST api resources");
        packages(true, "com.nwnx.api");

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider(mapper);
        registerInstances(jacksonJsonProvider);
    }
}
