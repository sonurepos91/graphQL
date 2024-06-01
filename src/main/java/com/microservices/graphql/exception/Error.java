package com.microservices.graphql.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Error implements Serializable {

    private String code;
    private String field;
    private String message;
}
