package com.example.jee.playground.persistence.model;

import lombok.Builder;

@Builder
public record TodoSearchRequest(Long userId, String userUsername, Boolean completed) {

}
