package com.example.habitgoservice.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Todo {
    private int id;
    private String name;
    private String description;
    private String finishDate;
    private int isFinished;
    private int type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
