package com.example.jee.playground.persistence.repository;

import com.example.jee.playground.persistence.entity.User;
import jakarta.ejb.Stateless;

@Stateless
public class UserRepository extends DefaultCrudRepository<User, Long> {

  public UserRepository() {
    super(User.class);
  }

}