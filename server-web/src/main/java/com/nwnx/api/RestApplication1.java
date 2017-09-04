package com.nwnx.api;

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
    }
}
