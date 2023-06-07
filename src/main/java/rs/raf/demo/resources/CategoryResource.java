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

//  @GET
//  @Path("/{id}")
//  @Produces(MediaType.APPLICATION_JSON)
//  public Subject find(@PathParam("id") Integer id) {
//    return this.newsService.findNews(id);
//  }

  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") Integer id) {
    this.categoryService.deleteCategory(id);
  }
}
