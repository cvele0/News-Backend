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
//  public Subject find(@PathParam("id") Integer id) {
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
}
