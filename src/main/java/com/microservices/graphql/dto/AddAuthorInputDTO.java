package com.microservices.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddAuthorInputDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<BookInputDTO> books;
}
