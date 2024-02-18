package com.thoughtclan.TodoPro.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class DbNames {

    @Id
    private int id;

    private String dbname;
}
