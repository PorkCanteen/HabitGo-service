package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.entity.User;
import com.example.habitgoservice.mapper.UserMapper;
import com.example.habitgoservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.listUser();
    }
}
