package rs.raf.demo.filters;

import rs.raf.demo.resources.CategoryResource;
import rs.raf.demo.resources.CommentResource;
import rs.raf.demo.resources.NewsResource;
import rs.raf.demo.resources.UserResource;
import rs.raf.demo.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
//        if (true) return;

        if (requestContext.getUriInfo().getPath().contains("login")) return;

        List<Object> matchedResources = requestContext.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {
            if (matchedResource instanceof UserResource) {
                try {
                    String token = requestContext.getHeaderString("Authorization");
                    if(token != null && token.startsWith("Bearer ")) {
                        token = token.replace("Bearer ", "");
                    }

                    if (!this.userService.isAuthorized(token)) {
                        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    }
                } catch (Exception exception) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } else if (matchedResource instanceof NewsResource) {

            } else if (matchedResource instanceof CommentResource) {

            } else if (matchedResource instanceof CategoryResource) {

            }
        }
    }
}
