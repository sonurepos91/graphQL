package com.microservices.graphql.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException{

    private String field;
    private String code;
    private String message;
}
