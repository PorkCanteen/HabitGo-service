package com.example.habitgoservice.controller;

import com.example.habitgoservice.common.Result;
import com.example.habitgoservice.controller.dto.LoginDTO;
import com.example.habitgoservice.controller.request.LoginRequest;
import com.example.habitgoservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public Result listUsers() {
        return Result.success(userService.listUsers());
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        LoginDTO login = userService.login(request);
        return Result.success(login);
    }
}
