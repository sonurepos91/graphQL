package com.microservices.graphql.controller;

import com.microservices.graphql.dto.AddAuthorInputDTO;
import com.microservices.graphql.entity.Author;
import com.microservices.graphql.entity.Todo;
import com.microservices.graphql.processor.ToDoProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostProcessorController<T> {

    Logger log = LogManager.getLogger(PostProcessorController.class);

    @Autowired
    private ToDoProcessor toDoProcessor;

    @QueryMapping(name = "todo")
    public Todo getToDoById(@Argument("id") Long id){
        return toDoProcessor.findToDos(id);
    }

    @QueryMapping(name ="findByIdAndTextMessage")
    public Todo fetchByIdAndText(@Argument("id") Long id, @Argument("text") String text){
        return toDoProcessor.findByIdAndTextMessage(id,text);
    }
    @QueryMapping
    public List<Todo> getAllTodos(){
        return toDoProcessor.finalAllEntities();
    }

    @MutationMapping
    public Todo addTodoReturnTodo (@Argument("text") String text, @Argument("completed") Boolean completed){
        log.info("............. Adding new ToDo call started ..........");
        return toDoProcessor.addnewToDo(text, completed);
    }

    @MutationMapping(name = "addTodo")
    public Long saveToDoReturningId(@Argument("text") String text, @Argument("completed") Boolean value){
        log.info("............. Adding new ToDo call started ..........");
        return toDoProcessor.addToDo(text, value);
    }

    @MutationMapping
    public Todo updateTodo(@Argument("id") Long id, @Argument("text") String value , @Argument("completed") Boolean arg){
        return toDoProcessor.updateToDo(id,value,arg);
    }

    @MutationMapping
    public Long deleteTodo(@Argument("id") Long id){
        return toDoProcessor.deleteTodoEntity(id);
    }

    @MutationMapping
    public Long addAuthor(@Argument AddAuthorInputDTO addInputDetails){
       log.info(":::::::  createAuthor call started :::::: ");
        return toDoProcessor.saveAuthor(addInputDetails);
    }

    @QueryMapping
    public Author getAuthor(@Argument String firstName){
        return toDoProcessor.findAllAuthors(firstName);
    }
}
