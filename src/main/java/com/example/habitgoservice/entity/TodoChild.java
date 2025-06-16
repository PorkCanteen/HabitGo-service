package com.example.habitgoservice.entity;

import lombok.Data;

@Data
public class TodoChild {
    private long id;
    private String content;
    private int isCompleted; // 0: 未完成, 1: 已完成
} 