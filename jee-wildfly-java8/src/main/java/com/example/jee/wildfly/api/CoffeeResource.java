package com.example.jee.wildfly.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import com.example.jee.wildfly.entity.Coffee;
import com.example.jee.wildfly.repository.CoffeeRepository;
import java.util.List;
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
      log.info("Error creating coffee {}", coffee.getName());
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
      log.info("Error deleting coffee by id {}", id);
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
  }


  @PUT
  @Consumes(APPLICATION_JSON)
  @Produces(APPLICATION_JSON)
  public Coffee update(Coffee coffee) {
    log.info("Updating coffee {}", coffee.getName());
    try {
      return cafeRepository.create(coffee);
    } catch (PersistenceException ex) {
      log.info("Error updating coffee {}", coffee.getName());
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }
}