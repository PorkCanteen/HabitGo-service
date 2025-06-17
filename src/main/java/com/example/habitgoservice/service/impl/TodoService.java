package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.entity.Todo;
import com.example.habitgoservice.entity.TodoChild;
import com.example.habitgoservice.mapper.TodoMapper;
import com.example.habitgoservice.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
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
        // 设置默认的children为空数组
        if (todo.getChildren() == null) {
            todo.setChildren(new ArrayList<>());
        }
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

    @Override
    public Todo getTodoById(int id, int userId) {
        return todoMapper.getTodoById(id, userId);
    }

    @Override
    public void updateTodoChildren(int id, int userId, List<TodoChild> children) {
        todoMapper.updateTodoChildren(id, userId, children, LocalDateTime.now());
    }

    @Override
    public void toggleChildComplete(int todoId, long childId, int userId) {
        // 获取当前todo
        Todo todo = todoMapper.getTodoById(todoId, userId);
        if (todo != null && todo.getChildren() != null) {
            // 找到对应的child并切换状态
            for (TodoChild child : todo.getChildren()) {
                if (child.getId() == childId) {
                    child.setIsCompleted(child.getIsCompleted() == 1 ? 0 : 1);
                    break;
                }
            }
            // 更新children
            todoMapper.updateTodoChildren(todoId, userId, todo.getChildren(), LocalDateTime.now());
        }
    }
}

