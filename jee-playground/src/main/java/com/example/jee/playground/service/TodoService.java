package com.example.jee.playground.service;


import com.example.jee.playground.persistence.model.TodoSearchRequest;
import com.example.services.todolist.api.v1.model.Todo;
import java.util.List;

public interface TodoService extends
    CrudService<Todo, com.example.jee.playground.persistence.entity.Todo, Long> {

  List<Todo> search(TodoSearchRequest request);

}
