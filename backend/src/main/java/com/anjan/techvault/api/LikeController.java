package com.anjan.techvault.api;

import com.anjan.techvault.domain.like.Like;
import com.anjan.techvault.service.like.LikeService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.quarkus.security.Authenticated;

@Path("/api/v1/likes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LikeController {

    private final LikeService likeService;

    @Inject
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @POST
    @RolesAllowed({"user"})
    public Response addLike(Like like) {
        return Response.ok(likeService.addLike(like)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"user"})
    public Response removeLike(@PathParam("id") Long id) {
        likeService.removeLike(id);
        return Response.noContent().build();
    }
}