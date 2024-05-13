package com.example.jee.playground.persistence.entity;

public interface Identifiable<T> {

  T getId();

  void setId(T id);

  default boolean isNew() {
    return getId() == null;
  }

}
