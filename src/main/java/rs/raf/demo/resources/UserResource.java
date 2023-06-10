package rs.raf.demo.resources;

import rs.raf.demo.entities.User;
import rs.raf.demo.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

  @Inject
  private UserService userService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response all() {
    return Response.ok(this.userService.allUsers()).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public User create(@Valid User user) {
    return this.userService.addUser(user);
  }

//  @GET
//  @Path("/{id}")
//  @Produces(MediaType.APPLICATION_JSON)
//  public Subject find(@PathParam("id") Integer id) {
//    return this.newsService.findNews(id);
//  }

  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") Integer id) {
    this.userService.deleteUser(id);
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUser(@PathParam("id") Integer id,  User user) {
    try {
      // Validate the category ID or perform any necessary checks

      // Update the category using the provided category object
      user.setId(id);
      userService.updateUser(user);

      // Return a successful response
      return Response.ok().build();
    } catch (Exception e) {
      // Handle any exceptions or errors
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update category").build();
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getNews(@PathParam("id") Integer id) {
    return this.userService.getUser(id);
  }
}
