package com.example.jee.playground.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@ApplicationScoped
public class ConfigPropertiesFactory {

  @Inject
  private ObjectMapper objectMapper;
  private final ObjectMapper objectMapperYaml = JsonMapper.builder(new YAMLFactory()).build();
//  @Resource(lookup = "java:global/jee-playground-config")
  private String configFile = "config/application.yml";

  @Inject
  public ConfigPropertiesFactory() {
    super();
  }

  public ConfigPropertiesFactory(ObjectMapper objectMapper, String configFile) {
    this.objectMapper = objectMapper;
    this.configFile = configFile;
  }

  @Produces
  public ConfigProperties configProperties() throws IOException {
    return getConfigProperties(configFile);
  }

  public ConfigProperties getConfigProperties(String path) throws IOException {
    File file = new File(path);
    if (!file.exists()) {
      throw new FileNotFoundException(path);
    }
    ObjectMapper fileMapper = getObjectMapperByType(path);
    return fileMapper.readValue(file, ConfigProperties.class);
  }

  public ObjectMapper getObjectMapperByType(String path) {
    if (path.endsWith("yaml") || path.endsWith("yml")) {
      return objectMapperYaml;
    } else {
      return objectMapper;
    }
  }
}
