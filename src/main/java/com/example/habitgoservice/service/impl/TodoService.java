package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.entity.Todo;
import com.example.habitgoservice.mapper.TodoMapper;
import com.example.habitgoservice.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class TodoService implements ITodoService {

    @Autowired
    TodoMapper todoMapper;

    @Override
    public List<Todo> listTodo(int userId) {
        return todoMapper.listTodo(userId);
    }

    @Override
    public void addTodo(Todo todo) {
        LocalDateTime now = LocalDateTime.now();
        todo.setCreateTime(now);
        todo.setUpdateTime(now);
        todoMapper.addTodo(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
        todo.setUpdateTime(LocalDateTime.now());
        todoMapper.updateTodo(todo);
    }

    @Override
    public void deleteTodo(int id, int userId) {
        todoMapper.deleteTodo(id, userId);
    }

    @Override
    public void toggleComplete(int id, int userId) {
        todoMapper.toggleComplete(id, userId);
    }
}

