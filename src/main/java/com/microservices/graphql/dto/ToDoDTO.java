package com.microservices.graphql.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ToDoDTO {
    private Long id;
    private String text;
    private boolean completed;
}
