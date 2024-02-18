package com.thoughtclan.TodoPro.Srevice;

import com.thoughtclan.TodoPro.TodoDto.TodoDto;
import com.thoughtclan.TodoPro.TodoDto.utils.TodoResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

public interface TodoService {

    public TodoDto create(TodoDto todoDto);

    public TodoDto getById(int id);

    public TodoResponse getAllTodos(int pageno, int pageSize, String soryBy, String sortDir);

    public TodoDto updateTodo(int id, TodoDto todo) throws Exception;

    public TodoDto partialUpdate(TodoDto input);


    String deleteTodo(int id);

}
