package com.thoughtclan.TodoPro.TodoDto;

import com.thoughtclan.TodoPro.Entity.TodoStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TodoDto {

    private int id;
    private String name;

    private Date due;

    private TodoStatus status;
}
