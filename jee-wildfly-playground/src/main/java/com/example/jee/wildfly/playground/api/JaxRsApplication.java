package com.example.jee.wildfly.playground.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("/api")
public class JaxRsApplication extends Application {

}
