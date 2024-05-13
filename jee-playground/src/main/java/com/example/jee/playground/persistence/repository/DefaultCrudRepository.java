package com.example.jee.playground.persistence.repository;

import com.example.jee.playground.persistence.entity.Identifiable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultCrudRepository<T extends Identifiable<ID>, ID> implements
    CrudRepository<T, ID> {

  private final Class<T> entityClass;
  @PersistenceContext
  protected EntityManager entityManager;

  @Override
  public List<T> findAll() {
    return entityManager.createQuery(
            "Select e FROM %s e".formatted(entityClass.getSimpleName()),
            entityClass
        ).getResultList();
  }

  @Override
  public Optional<T> findById(ID id) {
    return Optional.ofNullable(entityManager.find(entityClass, id));
  }

  @Override
  public T getReference(ID id) {
    return entityManager.getReference(entityClass, id);
  }

  @Override
  public T save(T entity) {
    if (entity.isNew()) {
      entityManager.persist(entity);
      return entity;
    } else {
      return entityManager.merge(entity);
    }
  }

  @Override
  public T patch(ID id, UnaryOperator<T> patch) {
    T existingEntity = findById(id)
        .orElseThrow(IllegalStateException::new);
    existingEntity = patch.apply(existingEntity);
    return entityManager.merge(existingEntity);
  }

  @Override
  public void deleteById(ID id) {
    T entity = findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
    entityManager.remove(entity);
  }
}