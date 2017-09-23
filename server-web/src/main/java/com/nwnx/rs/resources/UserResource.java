package com.nwnx.rs.resources;

import com.nwnx.components.validation.BeanValidator;
import com.nwnx.persistence.entities.User;
import com.nwnx.rs.dto.requests.UserRequest;
import com.nwnx.rs.dto.responses.UserResponse;
import com.nwnx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    @Autowired
    private BeanValidator validator;

    @Autowired(required = true)
    @Qualifier("userService")
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserRequest userRequest) {
        validator.validate(userRequest);

        User user = userService.addUser(userRequest);
        UserResponse response = new UserResponse(user.getId(), user.getCreatedOn(),
                user.getModifiedOn(), user.getFullName(), user.getName(),
                user.getEmail(), user.getPassword());
        return Response.ok(response).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUser(@PathParam("id") long userId) {
        User user = userService.getUser(userId);

        UserResponse response = new UserResponse(user.getId(), user.getCreatedOn(),
                user.getModifiedOn(), user.getFullName(), user.getName(),
                user.getEmail(), user.getPassword());
        return Response.ok(response).build();
    }
}
