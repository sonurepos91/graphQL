package com.microservices.graphql.repository;

import com.microservices.graphql.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    Todo findByIdAndText (Long id, String text);
}
