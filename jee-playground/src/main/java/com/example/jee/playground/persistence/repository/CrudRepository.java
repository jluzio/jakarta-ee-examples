package com.example.jee.playground.persistence.repository;

import com.example.jee.playground.persistence.entity.Identifiable;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

public interface CrudRepository<T extends Identifiable<ID>, ID> {

  List<T> findAll();

  Optional<T> findById(ID id);

  T getReference(ID id);

  T save(T entity);

  T patch(ID id, UnaryOperator<T> patch);

  void deleteById(ID id);

}