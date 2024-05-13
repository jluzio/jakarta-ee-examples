package com.example.jee.playground.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonProvider implements ContextResolver<ObjectMapper> {

  @Inject
  private ObjectMapper objectMapper;

  @Override
  public ObjectMapper getContext(Class type) {
    return objectMapper;
  }
}
