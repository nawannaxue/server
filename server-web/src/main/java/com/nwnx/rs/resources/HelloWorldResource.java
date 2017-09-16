package com.nwnx.rs.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldResource {

    @GET
    public Response hello() {
        return Response.ok().build();
    }
}
