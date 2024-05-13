package com.example.jee.playground.persistence.repository;

import com.example.jee.playground.persistence.entity.Todo;
import com.example.jee.playground.persistence.entity.Todo_;
import com.example.jee.playground.persistence.entity.User_;
import com.example.jee.playground.persistence.model.TodoSearchRequest;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TodoRepository extends DefaultCrudRepository<Todo, Long> {

  @Inject
  public TodoRepository() {
    super(Todo.class);
  }

  public List<Todo> search(TodoSearchRequest request) {
    var cb = entityManager.getCriteriaBuilder();
    var query = cb.createQuery(Todo.class);
    var root = query.from(Todo.class);
    var userPath = root.join(Todo_.user);

    List<Predicate> whereItems = new ArrayList<>();
    if (request.completed() != null) {
      whereItems.add(
          cb.equal(root.get(Todo_.completed), request.completed()));
    }
    if (request.userId() != null) {
      whereItems.add(
          cb.equal(userPath.get(User_.id), request.userId()));
    }
    if (request.userUsername() != null) {
      whereItems.add(
          Predicates.textSearch(cb, userPath.get(User_.username), request.userUsername()));
    }

    query
        .where(whereItems.toArray(new Predicate[0]))
        .orderBy(cb.asc(root.get(Todo_.createdDate)));
    return entityManager.createQuery(query)
        .getResultList();
  }
}