package com.example.habitgoservice.service;

import com.example.habitgoservice.entity.Task;

import java.util.List;

public interface

ITaskService {
    List<Task> listTask();

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(int id);

    /**
     * 切换任务状态
     * @param id 任务ID
     */
    void toggleTaskStatus(int id);

    /**
     * 重置所有任务状态
     */
    void resetAllTaskStatus();
}
