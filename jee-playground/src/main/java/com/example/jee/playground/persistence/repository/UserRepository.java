package com.example.jee.playground.persistence.repository;

import com.example.jee.playground.persistence.entity.User;
import com.example.jee.playground.persistence.entity.User_;
import com.example.jee.playground.persistence.model.UserSearchRequest;
import jakarta.ejb.Stateless;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserRepository extends DefaultCrudRepository<User, Long> {

  public UserRepository() {
    super(User.class);
  }

  public List<User> search(UserSearchRequest request) {
    var cb = entityManager.getCriteriaBuilder();
    var query = cb.createQuery(User.class);
    var root = query.from(User.class);

    List<Predicate> whereItems = new ArrayList<>();
    if (request.name() != null) {
      whereItems.add(
          Predicates.textSearch(cb, root.get(User_.name), request.name()));
    }
    if (request.username() != null) {
      whereItems.add(
          Predicates.textSearch(cb, root.get(User_.username), request.username()));
    }

    query
        .where(whereItems.toArray(new Predicate[0]))
        .orderBy(cb.asc(root.get(User_.id)));
    return entityManager.createQuery(query)
        .getResultList();
  }
}