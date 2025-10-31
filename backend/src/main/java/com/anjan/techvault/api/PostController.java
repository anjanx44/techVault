package com.anjan.techvault.api;

import com.anjan.techvault.domain.post.Post;
import com.anjan.techvault.domain.user.User;
import com.anjan.techvault.dto.CreatePostRequest;
import com.anjan.techvault.service.post.PostService;
import com.anjan.techvault.service.user.UserService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @Inject
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @POST
    @PermitAll
    @Transactional
    public Response createPost(CreatePostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        
        // For testing, use the first user or create a dummy user
        User user = userService.findByUsername("anjanx44")
                .orElseThrow(() -> new WebApplicationException("User not found", 400));
        post.setUser(user);
        
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