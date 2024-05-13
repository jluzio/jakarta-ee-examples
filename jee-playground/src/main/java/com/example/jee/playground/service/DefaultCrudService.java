package com.example.jee.playground.service;

import com.example.jee.playground.persistence.entity.Identifiable;
import com.example.jee.playground.persistence.repository.CrudRepository;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultCrudService<S, E extends Identifiable<ID>, ID, R extends CrudRepository<E, ID>>
    implements CrudService<S, E, ID> {

  protected final R repository;
  protected final Function<S, E> mapToPersistence;
  protected final Function<E, S> mapToPresentation;
  protected final BinaryOperator<E> updatePatch;
  protected final Predicate<E> validateUpdate;

  @Override
  public S findById(ID id) {
    return repository.findById(id)
        .map(mapToPresentation)
        .orElseThrow(this::handleNotFound);
  }

  @Override
  public List<S> findAll() {
    return repository.findAll().stream()
        .map(mapToPresentation)
        .toList();
  }

  @Override
  public S save(S serviceDto) {
    var entity = mapToPersistence.apply(serviceDto);
    entity = repository.save(entity);
    return mapToPresentation.apply(entity);
  }

  @Override
  public S patch(ID id, S serviceDto) {
    E existingEntity = repository.findById(id)
        .orElseThrow(this::handleNotFound);
    if (!validateUpdate.test(existingEntity)) {
      throw new WebApplicationException("Data is locked", Status.BAD_REQUEST);
    }

    E patchEntity = mapToPersistence.apply(serviceDto);
    UnaryOperator<E> patch = existing -> updatePatch.apply(existing, patchEntity);
    E updatedEntity = repository.patch(id, patch);
    return mapToPresentation.apply(updatedEntity);
  }

  @Override
  public void deleteById(ID id) {
    repository.deleteById(id);
  }

  private WebApplicationException handleNotFound() {
    return new WebApplicationException(Status.NOT_FOUND);
  }
}