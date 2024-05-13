package com.example.jee.playground.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.time.Clock;

@ApplicationScoped
public class TimeConfig {

  @Produces
  public Clock clock() {
    return Clock.systemUTC();
  }
}
