package com.example.jee.playground.persistence.repository;

import com.example.jee.playground.persistence.entity.Todo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class TodoRepository extends DefaultCrudRepository<Todo, Long> {

  @Inject
  public TodoRepository() {
    super(Todo.class);
  }
}