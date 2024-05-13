package com.example.jee.playground.api.todolist;

import com.example.jee.playground.persistence.model.TodoSearchRequest;
import com.example.jee.playground.service.TodoService;
import com.example.services.todolist.api.v1.TodoApi;
import com.example.services.todolist.api.v1.model.Todo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TodoResource implements TodoApi {

  @Inject
  private TodoService todoService;

  @Override
  public Todo createTodo(Todo todo) {
    return todoService.save(todo);
  }

  @Override
  public void deleteTodo(Long id) {
    todoService.deleteById(id);
  }

  @Override
  public Todo getTodo(Long id) {
    return todoService.findById(id);
  }

  @Override
  public List<Todo> listTodos(Long userId, String userUsername, Boolean completed) {
    return todoService.search(new TodoSearchRequest(userId, userUsername, completed));
  }

  @Override
  public Todo updateTodo(Long id, Todo todo) {
    return todoService.patch(id, todo);
  }
}