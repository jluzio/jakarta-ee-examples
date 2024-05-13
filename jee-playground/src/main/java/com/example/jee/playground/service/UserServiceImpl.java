package com.example.jee.playground.service;

import com.example.jee.playground.mapper.ApiMapper;
import com.example.jee.playground.mapper.PersistenceMapper;
import com.example.jee.playground.persistence.model.UserSearchRequest;
import com.example.jee.playground.persistence.repository.UserRepository;
import com.example.services.todolist.api.v1.model.Todo;
import com.example.services.todolist.api.v1.model.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import lombok.extern.jbosslog.JBossLog;

@Stateless
@JBossLog
public class UserServiceImpl extends
    DefaultCrudService<User, com.example.jee.playground.persistence.entity.User, Long, UserRepository>
    implements UserService {

  private final ApiMapper apiMapper;

  @Inject
  public UserServiceImpl(
      UserRepository repository,
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
    this.apiMapper = apiMapper;
  }

  @Override
  public List<Todo> getUserTodos(Long id) {
    return repository.findById(id)
        .map(com.example.jee.playground.persistence.entity.User::getTodos)
        .stream()
        .flatMap(Collection::stream)
        .map(apiMapper::toPresentation)
        .toList();
  }

  @Override
  public List<User> search(UserSearchRequest request) {
    return repository.search(request).stream()
        .map(mapToPresentation)
        .toList();
  }
}