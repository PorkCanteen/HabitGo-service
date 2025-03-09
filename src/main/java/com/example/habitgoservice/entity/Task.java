package com.example.habitgoservice.entity;

import lombok.Data;

@Data
public class Task {
    private int id;
    private String name;
    private String description;
    private int count;
    private int isCompleted;
    private int taskType;
    private int targetType;
    private int targetCount;
}
