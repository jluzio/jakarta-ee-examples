package com.example.wildfly.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class HelloResource {

  @GET
  @Path("/")
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() {
    return Response.ok("Hello").build();
  }

}