package com.example.jee.playground.persistence.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Predicates {

  public static Predicate textSearch(CriteriaBuilder cb, Expression<String> expression,
      String text) {
    return cb.like(cb.lower(expression), textSearchPattern(text));
  }

  public static String textSearchPattern(String text) {
    return "%" + text.toLowerCase() + "%";
  }
}
