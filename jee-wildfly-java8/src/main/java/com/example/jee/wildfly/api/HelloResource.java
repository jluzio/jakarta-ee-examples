package com.example.jee.wildfly.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloResource {

  @GET
  @Path("/")
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() {
    return Response.ok("Hello").build();
  }

}