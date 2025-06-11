package com.example.habitgoservice.service;

import com.example.habitgoservice.controller.dto.TaskDetailDTO;
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

    /**
     * 获取任务详情
     * @param id 任务ID
     * @param userId 用户ID
     * @return 任务详情DTO
     */
    TaskDetailDTO getTaskDetail(int id, int userId);
}
