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
}
