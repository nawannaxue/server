package com.nwnx.rs.resources;

import com.nwnx.components.validation.BeanValidator;
import com.nwnx.persistence.entities.User;
import com.nwnx.rs.dto.requests.UserRequest;
import com.nwnx.rs.dto.responses.UserResponse;
import com.nwnx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.*;
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

        User user = userService.addUser(userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getEmail(),
                userRequest.getSex(),
                userRequest.getPassword());

        UserResponse response = new UserResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setSex(user.getSex());
        response.setUserId(user.getUserId());

        return Response.ok(response).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUser(@PathParam("id") long userId) {
        User user = userService.getUser(userId);

        UserResponse response = new UserResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setSex(user.getSex());
        response.setUserId(user.getUserId());

        return Response.ok(response).build();
    }
}
