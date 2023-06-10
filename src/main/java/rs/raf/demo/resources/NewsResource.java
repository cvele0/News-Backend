package rs.raf.demo.resources;

import rs.raf.demo.entities.News;
import rs.raf.demo.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/news")
public class NewsResource {

  @Inject
  private NewsService newsService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response all() {
    return Response.ok(this.newsService.allNews()).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public News create(@Valid News news) {
    return this.newsService.addNews(news);
  }

//  @GET
//  @Path("/{id}")
//  @Produces(MediaType.APPLICATION_JSON)
//  public News find(@PathParam("id") Integer id) {
//    return this.newsService.findNews(id);
//  }

  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") Integer id) {
    this.newsService.deleteNews(id);
  }

  @GET
  @Path("/lastTen")
  @Produces(MediaType.APPLICATION_JSON)
  public Response lastTen() {
    return Response.ok(this.newsService.lastTen()).build();
  }

  @GET
  @Path("/mostRead")
  @Produces(MediaType.APPLICATION_JSON)
  public Response mostRead() {
    return Response.ok(this.newsService.mostRead()).build();
  }

  @GET
  @Path("/byCategory/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response byCategory(@PathParam("id") Integer id) {
    return Response.ok(this.newsService.byCategory(id)).build();
  }

  @GET
  @Path("/getNews/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNews(@PathParam("id") Integer id) {
    return Response.ok(this.newsService.getNews(id)).build();
  }

  @PUT
  @Path("/incrementViewCount/{id}")
  public Response incrementViewCount(@PathParam("id") Integer id, @QueryParam("count") Integer count) {
    this.newsService.incrementViewCount(id, count);
    return Response.ok().build();
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateNews(@PathParam("id") Integer id,  News news) {
    try {
      // Validate the category ID or perform any necessary checks

      // Update the category using the provided category object
      news.setId(id);
      newsService.updateNews(news);

      // Return a successful response
      return Response.ok().build();
    } catch (Exception e) {
      // Handle any exceptions or errors
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update category").build();
    }
  }
}
