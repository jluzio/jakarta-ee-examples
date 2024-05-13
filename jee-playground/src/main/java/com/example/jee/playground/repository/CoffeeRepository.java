package com.example.jee.playground.repository;

import com.example.jee.playground.entity.Coffee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import lombok.extern.jbosslog.JBossLog;

@Stateless
@JBossLog
public class CoffeeRepository {

  @PersistenceContext
  private EntityManager em;

  public Coffee create(Coffee coffee) {
    log.infof("Creating coffee %s", coffee.getName());
    em.persist(coffee);

    return coffee;
  }

  public List<Coffee> findAll() {
    log.infof("Getting all coffee");
    return em.createQuery("SELECT c FROM Coffee c", Coffee.class).getResultList();
  }

  public Optional<Coffee> findById(Long id) {
    log.infof("Getting coffee by id %s", id);
    return Optional.ofNullable(em.find(Coffee.class, id));
  }

  public void delete(Long id) {
    log.infof("Deleting coffee by id %s", id);
    var coffee = findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid coffee Id:" + id));
    em.remove(coffee);
  }

  public Coffee update(Coffee coffee) {
    log.infof("Updating coffee %s", coffee.getName());
    return em.merge(coffee);
  }
}