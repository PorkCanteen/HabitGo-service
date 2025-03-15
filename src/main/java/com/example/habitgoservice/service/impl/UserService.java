package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.controller.dto.LoginDTO;
import com.example.habitgoservice.controller.request.LoginRequest;
import com.example.habitgoservice.entity.User;
import com.example.habitgoservice.mapper.UserMapper;
import com.example.habitgoservice.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        return userMapper.listUser();
    }

    @Override
    public LoginDTO login(LoginRequest request) {
        LoginDTO loginDTO = null;
        try {
            User user = userMapper.getByUserNameAndPassword(request);
            loginDTO = new LoginDTO();
            BeanUtils.copyProperties(user, loginDTO);
            return loginDTO;
        } catch (Exception e) {
            log.error("登录失败", e);
            return null;
        }
    }
}
