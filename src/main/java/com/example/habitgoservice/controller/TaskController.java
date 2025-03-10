package com.example.habitgoservice.controller;

import com.example.habitgoservice.common.Result;
import com.example.habitgoservice.entity.Task;
import com.example.habitgoservice.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ITaskService taskService;

    // 查询列表
    @GetMapping("/list")
    public Result listTask() {
        return Result.success(taskService.listTask());
    }

    // 创建任务
    @PostMapping
    public Result addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return Result.success();
    }

    // 修改任务
    @PutMapping("/{id}")
    public Result updateTask(
            @PathVariable int id,
            @Validated @RequestBody Task task) {
        task.setId(id); // 绑定路径参数到对象
        taskService.updateTask(task);
        return Result.success("更新成功");
    }

    // 删除任务
    @DeleteMapping("/{id}")
    public Result deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return Result.success("删除成功");
    }
}
