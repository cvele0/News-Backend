package rs.raf.demo.resources;

import rs.raf.demo.entities.Category;
import rs.raf.demo.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categories")
public class CategoryResource {

  @Inject
  private CategoryService categoryService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response all() {
    return Response.ok(this.categoryService.allCategories()).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Category create(@Valid Category category) {
    return this.categoryService.addCategory(category);
  }

  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") Integer id) {
    this.categoryService.deleteCategory(id);
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateCategory(@PathParam("id") Integer id, Category category) {
    try {
      // Validate the category ID or perform any necessary checks

      // Update the category using the provided category object
      category.setId(id);
      categoryService.updateCategory(category);

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
  public Response getCategory(@PathParam("id") Integer id) {
    return Response.ok(this.categoryService.getCategory(id)).build();
  }
}
