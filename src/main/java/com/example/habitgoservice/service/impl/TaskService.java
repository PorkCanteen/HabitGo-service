package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.entity.Task;
import com.example.habitgoservice.mapper.TaskMapper;
import com.example.habitgoservice.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    TaskMapper taskMapper;

    @Override
    public List<Task> listTask() {
        return taskMapper.listTask();
    }

    @Override
    public void addTask(Task task) {
        taskMapper.addTask(task);
    }

    @Override
    public void updateTask(Task task) {
        taskMapper.updateTask(task);
    }

    @Override
    public void deleteTask(int id) {
        taskMapper.deleteTask(id);
    }

    @Override
    public void toggleTaskStatus(int id) {
        int result = taskMapper.toggleTaskStatus(id);
        if (result == 0) {
            throw new RuntimeException("任务不存在或更新失败");
        }
    }

    @Override
    public void resetAllTaskStatus() {
        taskMapper.resetAllTaskStatus();
    }
}
