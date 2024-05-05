package com.example.jee.wildfly.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.example.jee.wildfly.entity.Coffee;
import com.example.jee.wildfly.repository.CoffeeRepository;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Logger;

@Path("coffees")
public class CoffeeResource {

  private final Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @Inject
  private CoffeeRepository cafeRepository;

  @GET
  @Path("{id}")
  @Produces(APPLICATION_JSON)
  public Coffee findCoffee(@PathParam("id") Long id) {
    logger.info("Getting coffee by id " + id);
    return cafeRepository.findById(id)
        .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
  }

  @GET
  @Produces(APPLICATION_JSON)
  public List<Coffee> findAll() {
    logger.info("Getting all coffee");
    return cafeRepository.findAll();
  }

  @POST
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Coffee create(Coffee coffee) {
    logger.info("Creating coffee " + coffee.getName());
    try {
      return cafeRepository.create(coffee);
    } catch (PersistenceException ex) {
      logger.info("Error creating coffee " + coffee.getName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") Long id) {
    logger.info("Deleting coffee by id " + id);
    try {
      cafeRepository.delete(id);
    } catch (IllegalArgumentException e) {
      logger.info("Error deleting coffee by id " + id);
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
  }


  @PUT
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Coffee update(Coffee coffee) {
    logger.info("Updating coffee " + coffee.getName());
    try {
      return cafeRepository.create(coffee);
    } catch (PersistenceException ex) {
      logger.info("Error updating coffee " + coffee.getName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }
}