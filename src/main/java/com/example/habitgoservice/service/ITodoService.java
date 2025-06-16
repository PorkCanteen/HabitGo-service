package com.example.habitgoservice.service;

import com.example.habitgoservice.entity.Todo;
import com.example.habitgoservice.entity.TodoChild;

import java.util.List;

public interface ITodoService {
    List<Todo> listTodo(int userId);

    void addTodo(Todo todo);

    void updateTodo(Todo todo);

    void deleteTodo(int id, int userId);

    void toggleComplete(int id, int userId);

    Todo getTodoById(int id, int userId);

    void updateTodoChildren(int id, int userId, List<TodoChild> children);

    void toggleChildComplete(int todoId, long childId, int userId);
}
