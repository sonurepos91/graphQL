package com.microservices.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookInputDTO {
    private Long id;
    private String bookName;
    private Long authorId;
}
