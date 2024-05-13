package com.example.jee.playground.persistence.model;

import lombok.Builder;

@Builder
public record UserSearchRequest(String name, String username) {

}
