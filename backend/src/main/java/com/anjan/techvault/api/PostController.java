package com.anjan.techvault.api;

import com.anjan.techvault.domain.post.Post;
import com.anjan.techvault.service.post.PostService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import io.quarkus.security.Authenticated;

@Path("/api/v1/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostController {

    private final PostService postService;

    @Inject
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @POST
    @RolesAllowed({"user"})
    public Response createPost(Post post) {
        return Response.ok(postService.createPost(post)).build();
    }

    @GET
    @PermitAll
    public Response getAllPosts() {
        return Response.ok(postService.getAllPosts()).build();
    }

    @GET
    @Path("/search")
    @PermitAll
    public Response searchPostsByTitle(@QueryParam("keyword") String keyword) {
        return Response.ok(postService.searchPostsByTitle(keyword)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public Response deletePost(@PathParam("id") Long id) {
        postService.deletePost(id);
        return Response.noContent().build();
    }
}