package com.thoughtclan.TodoPro.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Todos_proj")
public class Todo {

    @Id
    @GeneratedValue
    @Column
    private  int id;
    @Column
    private String name;
    @Column
    private Date due;
    @Column
    private TodoStatus status;
}
