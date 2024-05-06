package com.example.jee.wildfly.repository;

import com.example.jee.wildfly.entity.Coffee;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;

@Stateless
@Log4j2
public class CoffeeRepository {

  @PersistenceContext
  private EntityManager em;

  public Coffee create(Coffee coffee) {
    log.info("Creating coffee {}", coffee.getName());
    em.persist(coffee);

    return coffee;
  }

  public List<Coffee> findAll() {
    log.info("Getting all coffee");
    return em.createQuery("SELECT c FROM Coffee c", Coffee.class).getResultList();
  }

  public Optional<Coffee> findById(Long id) {
    log.info("Getting coffee by id {}", id);
    return Optional.ofNullable(em.find(Coffee.class, id));
  }

  public void delete(Long id) {
    log.info("Deleting coffee by id {}", id);
    Coffee coffee = findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid coffee Id: " + id));
    em.remove(coffee);
  }

  public Coffee update(Coffee coffee) {
    log.info("Updating coffee {}", coffee.getName());
    return em.merge(coffee);
  }
}