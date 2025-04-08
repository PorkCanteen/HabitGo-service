package com.example.habitgoservice.service;

import com.example.habitgoservice.entity.Todo;

import java.util.List;

public interface

ITodoService {
    List<Todo> listTodo();

    void addTodo(Todo todo);

    void updateTodo(Todo todo);

    void deleteTodo(int id);

    void toggleComplete(int id);
}
