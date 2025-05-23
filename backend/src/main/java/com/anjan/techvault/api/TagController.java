package com.anjan.techvault.api;

import com.anjan.techvault.domain.tag.Tag;
import com.anjan.techvault.service.tag.TagService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import io.quarkus.security.Authenticated;

@Path("/api/v1/tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagController {

    private final TagService tagService;

    @Inject
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @POST
    @RolesAllowed({"admin"})
    public Response createTag(Tag tag) {
        return Response.ok(tagService.createTag(tag)).build();
    }

    @GET
    @Path("/{name}")
    @PermitAll
    public Response getTagByName(@PathParam("name") String name) {
        return tagService.getTagByName(name)
                .map(tag -> Response.ok(tag).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}