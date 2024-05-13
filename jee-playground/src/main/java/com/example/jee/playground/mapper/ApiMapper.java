package com.example.jee.playground.mapper;

import com.example.jee.playground.persistence.entity.Todo;
import com.example.jee.playground.persistence.entity.User;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ApiMapper {

  // _Todo
  @Mapping(target = "user.id", source = "userId")
  Todo toPersistence(com.example.services.todolist.api.v1.model.Todo todo);

  @Mapping(target = "userId", source = "user.id")
  com.example.services.todolist.api.v1.model.Todo toPresentation(Todo todo);

  // User
  User toPersistence(com.example.services.todolist.api.v1.model.User user);

  com.example.services.todolist.api.v1.model.User toPresentation(User user);

  // Other
  default Instant toInstant(OffsetDateTime dateTime) {
    return dateTime == null ? null : dateTime.toInstant();
  }

  default OffsetDateTime toInstant(Instant instant) {
    return instant == null ? null : instant.atOffset(ZoneOffset.UTC);
  }
}
