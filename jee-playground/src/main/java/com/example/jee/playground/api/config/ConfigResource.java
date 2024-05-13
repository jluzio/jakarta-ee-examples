package com.example.jee.playground.api.config;

import com.example.jee.playground.config.ConfigProperties;
import com.example.jee.playground.config.ConfigPropertiesFactory;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Path("config")
@Produces(MediaType.APPLICATION_JSON)
public class ConfigResource {

  @Inject
  ConfigPropertiesFactory configPropertiesFactory;

  @GET
  @Path("config-properties")
  public ConfigProperties getConfigProperties() throws IOException {
    return configPropertiesFactory.configProperties();
  }

  @GET
  @Path("config-properties/{path}")
  public ConfigProperties getConfigProperties(@PathParam("path") String path) throws IOException {
    return configPropertiesFactory.getConfigProperties(path);
  }

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
