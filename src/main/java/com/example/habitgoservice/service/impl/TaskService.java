package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.entity.Task;
import com.example.habitgoservice.mapper.TaskMapper;
import com.example.habitgoservice.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;  // 添加这行导入语句

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
        // 获取所有已完成的任务
        List<Task> completedTasks = taskMapper.getCompletedTasks();
        
        // 获取当前日期
        LocalDate today = LocalDate.now();
        
        // 为每个已完成的任务更新 completedDates
        for (Task task : completedTasks) {
            String currentDates = task.getCompletedDates();
            String newDate = today.toString(); // 格式：YYYY-MM-DD
            
            // 如果已有完成日期，则追加；否则直接设置
            String updatedDates = (currentDates != null && !currentDates.isEmpty()) 
                ? currentDates + "," + newDate 
                : newDate;
            
            task.setCompletedDates(updatedDates);
            taskMapper.updateCompletedDates(task);
        }
        
        // 重置所有任务状态
        taskMapper.resetAllTaskStatus();
    }
}
