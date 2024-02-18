package com.thoughtclan.TodoPro.TodoDto.utils;

import com.thoughtclan.TodoPro.TodoDto.TodoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {
    private List<TodoDto> content;

    private int pageNo;
    private int PageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
