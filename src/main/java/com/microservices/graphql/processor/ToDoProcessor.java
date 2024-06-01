package com.microservices.graphql.processor;

import com.microservices.graphql.dto.AddAuthorInputDTO;
import com.microservices.graphql.entity.Author;
import com.microservices.graphql.entity.Todo;

import java.util.List;

public interface ToDoProcessor {
    Todo addnewToDo (String text, Boolean complete);

    Long addToDo (String text, Boolean value);
    Todo findToDos (Long id);

    Todo findByIdAndTextMessage (Long id, String text);

    Todo updateToDo (Long id, String value, Boolean arg);

    Long deleteTodoEntity (Long id);

    List<Todo> finalAllEntities ();

    Long saveAuthor (AddAuthorInputDTO request);

    Author findAllAuthors (String firstName);
}
