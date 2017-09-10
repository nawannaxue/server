package com.nwnx.api.apps;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/users")
public class RestApplication extends ResourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(RestApplication.class);
    public RestApplication() {
        logger.info("Starting registering REST api resources");
        packages(true, "com.nwnx.api");
        packages(true, "org.nn");
    }
}