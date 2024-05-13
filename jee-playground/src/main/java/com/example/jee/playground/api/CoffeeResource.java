package com.example.jee.playground.api;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import com.example.jee.playground.entity.Coffee;
import com.example.jee.playground.repository.CoffeeRepository;
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
import lombok.extern.jbosslog.JBossLog;

@Path("coffees")
@JBossLog
public class CoffeeResource {

  @Inject
  private CoffeeRepository cafeRepository;

  @GET
  @Path("{id}")
  @Produces(APPLICATION_JSON)
  public Coffee findCoffee(@PathParam("id") Long id) {
    log.infof("Getting coffee by id %s", id);
    return cafeRepository.findById(id)
        .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
  }

  @GET
  @Produces(APPLICATION_JSON)
  public List<Coffee> findAll() {
    log.infof("Getting all coffee");
    return cafeRepository.findAll();
  }

  @POST
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Coffee create(Coffee coffee) {
    log.infof("Creating coffee %s", coffee.getName());
    try {
      return cafeRepository.create(coffee);
    } catch (PersistenceException ex) {
      log.infof("Error creating coffee " + coffee.getName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") Long id) {
    log.infof("Deleting coffee by id %s", id);
    try {
      cafeRepository.delete(id);
    } catch (IllegalArgumentException e) {
      log.infof("Error deleting coffee by id %s", id);
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
  }


  @PUT
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Coffee update(Coffee coffee) {
    log.infof("Updating coffee %s", coffee.getName());
    try {
      return cafeRepository.create(coffee);
    } catch (PersistenceException ex) {
      log.infof("Error updating coffee %s", coffee.getName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }
}