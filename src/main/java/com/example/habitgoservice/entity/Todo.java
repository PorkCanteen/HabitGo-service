package com.example.habitgoservice.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Data
public class Todo {
    private int id;
    private int userId;
    private String name;
    private String description;
    private String finishDate;
    private int isFinished;
    private int type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<TodoChild> children = new ArrayList<>();
}
