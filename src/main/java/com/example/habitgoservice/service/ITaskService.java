package com.example.habitgoservice.service;

import com.example.habitgoservice.entity.Task;

import java.util.List;

public interface

ITaskService {
    List<Task> listTask();

    void addTask(Task task);
}
