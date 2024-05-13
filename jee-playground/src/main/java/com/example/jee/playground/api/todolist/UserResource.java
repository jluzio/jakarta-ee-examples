package com.example.jee.playground.api.todolist;

import com.example.jee.playground.persistence.model.UserSearchRequest;
import com.example.jee.playground.service.UserService;
import com.example.services.todolist.api.v1.UserApi;
import com.example.services.todolist.api.v1.model.Todo;
import com.example.services.todolist.api.v1.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserResource implements UserApi {

  @Inject
  private UserService userService;

  @Override
  public User createUser(User user) {
    return userService.save(user);
  }

  @Override
  public void deleteUser(Long id) {
    userService.deleteById(id);
  }

  @Override
  public User getUser(Long id) {
    return userService.findById(id);
  }

  @Override
  public List<User> listUsers(String name, String username) {
    return userService.search(new UserSearchRequest(name, username));
  }

  @Override
  public User updateUser(Long id, User user) {
    return userService.patch(id, user);
  }

  @Override
  public List<Todo> getUserTodos(Long id) {
    return userService.getUserTodos(id);
  }
}