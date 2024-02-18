package com.thoughtclan.TodoPro.Repository;

import com.thoughtclan.TodoPro.Entity.Todo;
import com.thoughtclan.TodoPro.TodoDto.TodoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    List<Todo> findAll();
}
