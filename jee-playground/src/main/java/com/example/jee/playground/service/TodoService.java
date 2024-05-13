package com.example.jee.playground.service;


import com.example.services.todolist.api.v1.model.Todo;

public interface TodoService extends
    CrudService<Todo, com.example.jee.playground.persistence.entity.Todo, Long> {

}
