package com.thoughtclan.TodoPro.Controller;

import com.thoughtclan.TodoPro.Srevice.TodoService;
import com.thoughtclan.TodoPro.TodoDto.TodoDto;
import com.thoughtclan.TodoPro.TodoDto.utils.AppConstants;
import com.thoughtclan.TodoPro.TodoDto.utils.TodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Validated
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/todo/create")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto)
    {
        todoDto=todoService.create(todoDto);
        return new ResponseEntity<TodoDto>(todoDto, HttpStatus.CREATED);
    }
    @GetMapping("/todo/{id}")
    public ResponseEntity<TodoDto> getById(@PathVariable int id)
    {
        TodoDto output=todoService.getById(id);
        return new ResponseEntity<TodoDto>(output,HttpStatus.OK);
    }
    @GetMapping("/todo")
    public TodoResponse getAllTodos(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                    @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir)
    {
        return todoService.getAllTodos(pageNo,pageSize,sortBy,sortDir);
    }
    @PutMapping("/todo/update/{id}")
    public ResponseEntity<TodoDto> updateById(@PathVariable int id,@RequestBody TodoDto todoDto) throws Exception {
        TodoDto output=todoService.updateTodo(id,todoDto);
        return ResponseEntity.ok(output);
    }
    @PatchMapping("/todo/patch")
    public ResponseEntity<TodoDto> updateTodoById(@RequestBody TodoDto todoDto){
        TodoDto output=todoService.partialUpdate(todoDto);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTodo(@PathVariable int id)
    {
        return todoService.deleteTodo(id);
    }
}
