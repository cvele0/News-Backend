package rs.raf.demo.resources;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.Subject;
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
}
