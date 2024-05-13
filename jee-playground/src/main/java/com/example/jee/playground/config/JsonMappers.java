package com.example.jee.playground.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class JsonMappers {

  private final ObjectMapper objectMapper = JsonMapper.builder()
      .addModule(new JavaTimeModule())
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .build();

  @Produces
  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}
