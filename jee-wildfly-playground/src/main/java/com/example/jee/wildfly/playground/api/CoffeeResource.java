package com.example.jee.wildfly.playground.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import com.example.jee.wildfly.playground.entity.Coffee;
import com.example.jee.wildfly.playground.repository.CoffeeRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Path("coffees")
@Log4j2
public class CoffeeResource {

  @Inject
  private CoffeeRepository cafeRepository;

  @GET
  @Path("{id}")
  @Produces(APPLICATION_JSON)
  public Coffee findCoffee(@PathParam("id") Long id) {
    log.info("Getting coffee by id {}", id);
    return cafeRepository.findById(id)
        .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
  }

  @GET
  @Produces(APPLICATION_JSON)
  public List<Coffee> findAll() {
    log.info("Getting all coffee");
    return cafeRepository.findAll();
  }

  @POST
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Coffee create(Coffee coffee) {
    log.info("Creating coffee {}", coffee.getName());
    try {
      return cafeRepository.create(coffee);
    } catch (PersistenceException ex) {
      log.info("Error creating coffee " + coffee.getName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") Long id) {
    log.info("Deleting coffee by id {}", id);
    try {
      cafeRepository.delete(id);
    } catch (IllegalArgumentException e) {
      log.info("Error deleting coffee by id " + id);
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
  }


  @PUT
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Coffee update(Coffee coffee) {
    log.info("Updating coffee " + coffee.getName());
    try {
      return cafeRepository.create(coffee);
    } catch (PersistenceException ex) {
      log.info("Error updating coffee " + coffee.getName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }
}