package com.example.jee.playground.service;

import com.example.jee.playground.persistence.entity.Identifiable;
import java.util.List;

public interface CrudService<S, E extends Identifiable<ID>, ID> {

  List<S> findAll();

  S findById(ID id);

  S save(S serviceDto);

  S patch(ID id, S serviceDto);

  void deleteById(ID id);

}
