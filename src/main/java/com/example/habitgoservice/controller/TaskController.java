package com.example.habitgoservice.controller;

import com.example.habitgoservice.common.Result;
import com.example.habitgoservice.entity.Task;
import com.example.habitgoservice.service.ITaskService;
import com.example.habitgoservice.utils.RequestUtil;
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
        Integer userId = RequestUtil.getCurrentUserId();
        return Result.success(taskService.listTask(userId));
    }

    // 创建任务
    @PostMapping
    public Result addTask(@RequestBody Task task) {
        Integer userId = RequestUtil.getCurrentUserId();
        task.setUserId(userId);
        taskService.addTask(task);
        return Result.success();
    }

    // 修改任务
    @PutMapping("/{id}")
    public Result updateTask(
            @PathVariable int id,
            @Validated @RequestBody Task task) {
        Integer userId = RequestUtil.getCurrentUserId();
        task.setId(id); // 绑定路径参数到对象
        task.setUserId(userId);
        taskService.updateTask(task);
        return Result.success("更新成功");
    }

    // 删除任务
    @DeleteMapping("/{id}")
    public Result deleteTask(@PathVariable int id) {
        Integer userId = RequestUtil.getCurrentUserId();
        taskService.deleteTask(id, userId);
        return Result.success("删除成功");
    }

    // 切换任务状态
    @PutMapping("/toggle/{id}")
    public Result toggleTaskStatus(@PathVariable int id) {
        Integer userId = RequestUtil.getCurrentUserId();
        taskService.toggleTaskStatus(id, userId);
        return Result.success("状态切换成功");
    }
}
