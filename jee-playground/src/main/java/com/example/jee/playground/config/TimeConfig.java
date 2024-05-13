package com.example.jee.playground.config;

import java.time.Clock;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class TimeConfig {

  @Produces
  public Clock clock() {
    return Clock.systemUTC();
  }
}
