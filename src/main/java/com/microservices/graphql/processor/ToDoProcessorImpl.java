package com.microservices.graphql.processor;

import com.microservices.graphql.dto.AddAuthorInputDTO;
import com.microservices.graphql.entity.Author;
import com.microservices.graphql.entity.Book;
import com.microservices.graphql.entity.Todo;
import com.microservices.graphql.exception.EntityClassNotFoundException;
import com.microservices.graphql.repository.AuthorRepository;
import com.microservices.graphql.repository.BookRepository;
import com.microservices.graphql.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoProcessorImpl implements ToDoProcessor {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public Todo addnewToDo (String value, Boolean arg) {
        return todoRepository.save(Todo.builder().text(value).completed(arg).build());

    }

    @Override
    @Transactional
    public Long addToDo (String value, Boolean arg) {
        return todoRepository.save(Todo.builder().text(value).completed(arg).build()).getId();
    }

    @Override
    @Transactional
    public Todo findToDos (Long id) {
        return todoRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Todo findByIdAndTextMessage (Long id, String text) {
        return todoRepository.findByIdAndText(id,text);
    }

    @Override
    public Todo updateToDo (Long id, String value, Boolean arg) {
        Optional<Todo> optEntity = todoRepository.findById(id);
        if(optEntity.isPresent()){
            optEntity.get().setText(value);
            optEntity.get().setCompleted(arg);
            return todoRepository.save(optEntity.get());
        }
        return null;
    }

    @Override
    @Transactional
    public Long deleteTodoEntity (Long id) throws RuntimeException {
        Optional<Todo> entity = todoRepository.findById(id);
        if(entity.isPresent()){
            todoRepository.deleteById(id);
            return id;
        }
        throw new EntityClassNotFoundException("ID",String.valueOf(HttpStatus.NOT_FOUND),"Entity with Id : " +id+ " : not present");
    }

    @Override
    @Transactional
    public List<Todo> finalAllEntities () {
        return todoRepository.findAll();
    }

    @Override
    public Long saveAuthor (AddAuthorInputDTO request) {
        Long authorId = authorRepository.save(Author.builder()
                .firstName(request.getFirstName()).lastName(request.getLastName()).build()).getId();
       List<Book> booksList = new ArrayList<>();
       request.getBooks().forEach(book->{
           booksList.add(Book.builder().bookName(book.getBookName()).authorId(authorId).build());
       });
           bookRepository.saveAll(booksList);
        return authorId;
    }

    @Override
    @Transactional
    public Author findAllAuthors (String firstName) {
        return authorRepository.findByFirstName(firstName);
    }

}
