package com.example.habitgoservice.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Task {
    private int id;
    private int userId;
    private String name;
    private String description;
    private int count;
    private int isCompleted;
    private int taskType;
    private int targetType;
    private int targetCount;
    private String completedDates;  // 用于存储完成日期的字符串，格式如：2023-12-01,2023-12-02
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 添加 getter 和 setter 方法
    public String getCompletedDates() {
        return completedDates;
    }

    public void setCompletedDates(String completedDates) {
        this.completedDates = completedDates;
    }
}
