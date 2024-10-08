package com.stdbank.assessment.resource;

import com.stdbank.assessment.model.User;
import com.stdbank.assessment.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jakarta.inject.Inject;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(UserService userService){
        this.userService=userService;
    }
    @POST
    @Path("/create")
    public Response createUser(User user) {
        userService.addUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Path("/getUser/{id}")
    public User getUser(@PathParam("id") String id) {
        return userService.findUserById(Integer.parseInt(id));
    }
    @GET
    @Path("/getUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DELETE
    @Path("/deleteUser/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        User user = userService.findUserById(Integer.parseInt(id));
        userService.deleteUser(user);
        return Response.noContent().build();
    }

    @PUT
    @Path("/updateUser/{id}")
    public Response updateUser(@PathParam("id") String id, User user) {
        User existingUser = userService.findUserById(Integer.parseInt(id));
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userService.updateUser(user);
        return Response.ok(user).build();
    }
}
