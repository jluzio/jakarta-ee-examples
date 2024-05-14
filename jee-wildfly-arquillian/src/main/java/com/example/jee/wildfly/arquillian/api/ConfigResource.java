package com.example.jee.wildfly.arquillian.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Map;
import java.util.stream.Collectors;

@Path("api/config")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigResource {

  @GET
  @Path("env")
  public Map<String, String> getEnv() {
    return System.getenv();
  }

  @GET
  @Path("properties")
  public Map<String, String> getProperties() {
    return System.getProperties().entrySet().stream()
        .collect(Collectors.toMap(
            e -> String.valueOf(e.getKey()),
            e -> String.valueOf(e.getValue())
        ));
  }
}
