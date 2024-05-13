package com.example.jee.playground.mapper;

import com.example.jee.playground.persistence.entity.Todo;
import com.example.jee.playground.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface PersistenceMapper {

  default Todo update(@MappingTarget Todo target, Todo source) {
    target.setTitle(source.getTitle());
    return target;
  }

  default User update(@MappingTarget User target, User source) {
    target.setName(source.getName());
    target.setUsername(source.getName());
    target.setEmail(source.getEmail());
    return target;
  }
}
