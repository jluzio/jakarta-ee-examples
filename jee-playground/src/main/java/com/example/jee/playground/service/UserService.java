package com.example.jee.playground.service;


import com.example.jee.playground.persistence.model.UserSearchRequest;
import com.example.services.todolist.api.v1.model.Todo;
import com.example.services.todolist.api.v1.model.User;
import java.util.List;

public interface UserService extends
    CrudService<User, com.example.jee.playground.persistence.entity.User, Long> {

  List<Todo> getUserTodos(Long id);

  List<User> search(UserSearchRequest request);

}
