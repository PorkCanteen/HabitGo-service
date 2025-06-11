package com.example.habitgoservice.controller.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskDetailDTO {
    private int id;
    private int userId;
    private String name;
    private String description;
    private int count;
    private int isCompleted;
    private int taskType;
    private int targetType;
    private int targetCount;
    private String completedDates;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 统计字段
    private int weeklyCompletedCount;    // 本周打卡次数
    private int monthlyCompletedCount;   // 本月打卡次数
    private int totalCompletedCount;     // 总打卡次数
    private int continuities;            // 当前连续打卡次数
} 