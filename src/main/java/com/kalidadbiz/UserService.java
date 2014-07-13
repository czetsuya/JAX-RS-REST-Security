package com.kalidadbiz;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/user-service")
public class UserService {

	@GET
	@Path("/users/{id}")
	public Response getUserById(@PathParam("id") int id, @Context Request req) {
		Response.ResponseBuilder rb = Response.ok(UserDatabase.getUserById(id));
		return rb.build();
	}

	@RolesAllowed("ADMIN")
	@PUT
	@Path("/users/{id}")
	public Response updateUserById(@PathParam("id") int id) {
		// Update the User resource
		UserDatabase.updateUser(id);
		return Response.status(200).build();
	}
}