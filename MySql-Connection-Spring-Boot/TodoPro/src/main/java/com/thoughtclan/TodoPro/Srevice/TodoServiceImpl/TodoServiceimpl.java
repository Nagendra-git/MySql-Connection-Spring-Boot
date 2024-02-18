package com.thoughtclan.TodoPro.Srevice.TodoServiceImpl;

import com.thoughtclan.TodoPro.Entity.Todo;
import com.thoughtclan.TodoPro.Repository.TodoRepository;
import com.thoughtclan.TodoPro.Srevice.TodoService;
import com.thoughtclan.TodoPro.TodoDto.TodoDto;
import com.thoughtclan.TodoPro.TodoDto.utils.PatchMapper;
import com.thoughtclan.TodoPro.TodoDto.utils.TodoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceimpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PatchMapper patchMapper;

    @Override
    public TodoDto create(TodoDto input) {
        Todo todo=modelMapper.map(input,Todo.class);
        todo=todoRepository.save(todo);
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public TodoDto getById(int id) {
        Todo todo=todoRepository.findById(id).orElse(null);
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public TodoResponse getAllTodos(int pageno, int pageSize, String soryBy, String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(soryBy).ascending():Sort.by(soryBy).descending();
        Pageable pageable=PageRequest.of(pageno,pageSize,sort);
        Page<Todo> todos=todoRepository.findAll(pageable);
        List<TodoDto> content=todos.stream().map(todo -> mapToDTO(todo)).collect(Collectors.toList());

        TodoResponse todoResponse=new TodoResponse();
        todoResponse.setContent(content);
        todoResponse.setPageNo(todos.getNumber());
        todoResponse.setPageSize(todos.getSize());
        todoResponse.setTotalElements(todos.getTotalElements());
        todoResponse.setTotalPages(todos.getTotalPages());
        todoResponse.setLast(todos.isLast());
        return todoResponse;
    }
    private TodoDto mapToDTO(Todo todo)
    {
        TodoDto todoDto=new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setDue(todo.getDue());
        todoDto.setName(todo.getName());
        todoDto.setStatus(todo.getStatus());
        return todoDto;
    }


    @Override
    public TodoDto updateTodo(int id, TodoDto input) throws Exception {
        Todo todo=todoRepository.findById(id).orElseThrow(() ->new Exception("Task not found"));
        modelMapper.map(input,todo);
        Todo upadateTodo=todoRepository.save(todo);

          return modelMapper.map(upadateTodo,TodoDto.class);
    }

    @Override
    public TodoDto partialUpdate(TodoDto input)  {

        Todo todo=todoRepository.findById(input.getId()).orElse(null);
        patchMapper.map(input,todo);
        Todo updateTodo=todoRepository.save(todo);
        return patchMapper.map(updateTodo,TodoDto.class);
    }

    @Override
    public String deleteTodo(int id) {

        todoRepository.deleteById(id);
        return "Sucessfully deleted";

    }
}
