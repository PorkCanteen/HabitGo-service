package com.example.habitgoservice.controller;

import com.example.habitgoservice.common.Result;
import com.example.habitgoservice.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
