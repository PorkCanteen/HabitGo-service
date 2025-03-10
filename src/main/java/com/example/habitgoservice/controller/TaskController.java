package com.example.habitgoservice.controller;

import com.example.habitgoservice.common.Result;
import com.example.habitgoservice.entity.Task;
import com.example.habitgoservice.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ITaskService taskService;

    @GetMapping("/list")
    public Result listTask() {
        return Result.success(taskService.listTask());
    }

    @PostMapping
    public Result addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return Result.success();
    }
}
