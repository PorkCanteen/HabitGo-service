package com.example.habitgoservice.entity;

import lombok.Data;

@Data
public class Todo {
    private int id;
    private String name;
    private String description;
    private String finishDate;
    private int isFinished;
    private int type;
}
