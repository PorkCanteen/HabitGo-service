package com.example.habitgoservice.controller;

import com.example.habitgoservice.common.Result;
import com.example.habitgoservice.entity.Todo;
import com.example.habitgoservice.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    ITodoService todoService;

    // 查询列表
    @GetMapping("/list")
    public Result listTodo() {
        return Result.success(todoService.listTodo());
    }

    // 创建待办
    @PostMapping
    public Result addTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
        return Result.success();
    }

    // 修改待办
    @PutMapping("/{id}")
    public Result updateTodo(
            @PathVariable int id,
            @Validated @RequestBody Todo todo) {
        todo.setId(id); // 绑定路径参数到对象
        todoService.updateTodo(todo);
        return Result.success("更新成功");
    }

    // 删除待办
    @DeleteMapping("/{id}")
    public Result deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
        return Result.success("删除成功");
    }

    // 完成待办
    @PutMapping("/complete/{id}")
    public Result completeTodo(@PathVariable int id) {
        todoService.toggleComplete(id);
        return Result.success("操作成功");
    }

}
