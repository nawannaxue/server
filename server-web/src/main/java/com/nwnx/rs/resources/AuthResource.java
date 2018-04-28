package com.nwnx.rs.resources;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {

    @Path("/login")
    public Response login() {
        return Response.ok().build();
    }

    @Path("/logout")
    public Response logout() {
        return Response.ok().build();
    }

    @Path("/register")
    public Response register() {
        return Response.ok().build();
    }
}
