package com.example.habitgoservice.service;

import com.example.habitgoservice.entity.Task;

import java.util.List;

public interface ITaskService {
    List<Task> listTask(int userId);

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(int id, int userId);

    /**
     * 切换任务状态
     * @param id 任务ID
     * @param userId 用户ID
     */
    void toggleTaskStatus(int id, int userId);

    /**
     * 重置所有任务状态
     * @param userId 用户ID
     */
    void resetAllTaskStatus(int userId);
}
