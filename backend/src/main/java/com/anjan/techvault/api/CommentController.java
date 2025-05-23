package com.anjan.techvault.api;

import com.anjan.techvault.domain.comment.Comment;
import com.anjan.techvault.service.comment.CommentService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import io.quarkus.security.Authenticated;

@Path("/api/v1/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentController {

    private final CommentService commentService;

    @Inject
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @POST
    @RolesAllowed({"user"})
    public Response createComment(Comment comment) {
        return Response.ok(commentService.createComment(comment)).build();
    }

    @GET
    @Path("/post/{postId}")
    @PermitAll
    public Response getCommentsByPostId(@PathParam("postId") Long postId) {
        return Response.ok(commentService.getCommentsByPostId(postId)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public Response deleteComment(@PathParam("id") Long id) {
        commentService.deleteComment(id);
        return Response.noContent().build();
    }
}