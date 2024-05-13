package com.example.jee.playground.service;

import com.example.jee.playground.mapper.ApiMapper;
import com.example.jee.playground.mapper.PersistenceMapper;
import com.example.jee.playground.persistence.repository.TodoRepository;
import com.example.services.todolist.api.v1.model.Todo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.extern.jbosslog.JBossLog;

@Stateless
@JBossLog
public class TodoServiceImpl extends
    DefaultCrudService<Todo, com.example.jee.playground.persistence.entity.Todo, Long>
    implements TodoService {

  @Inject
  public TodoServiceImpl(
      TodoRepository repository,
      ApiMapper apiMapper,
      PersistenceMapper persistenceMapper
  ) {
    super(
        repository,
        apiMapper::toPersistence,
        apiMapper::toPresentation,
        persistenceMapper::update,
        user -> true
    );
  }
}