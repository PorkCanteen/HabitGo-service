package com.example.habitgoservice.service;

import com.example.habitgoservice.controller.dto.LoginDTO;
import com.example.habitgoservice.controller.request.LoginRequest;
import com.example.habitgoservice.entity.User;

import java.util.List;

public interface IUserService {
    List<User> listUsers();

    LoginDTO login(LoginRequest loginRequest);
}
