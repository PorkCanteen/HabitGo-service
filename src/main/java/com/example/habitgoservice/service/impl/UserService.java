package com.example.habitgoservice.service.impl;

import com.example.habitgoservice.common.Result;
import com.example.habitgoservice.controller.dto.LoginDTO;
import com.example.habitgoservice.controller.request.LoginRequest;
import com.example.habitgoservice.entity.User;
import com.example.habitgoservice.exception.ServiceException;
import com.example.habitgoservice.mapper.UserMapper;
import com.example.habitgoservice.service.IUserService;
import com.example.habitgoservice.utils.JwtUtils;
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
    
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public List<User> listUsers() {
        return userMapper.listUser();
    }

    @Override
    public LoginDTO login(LoginRequest request) {
        LoginDTO loginDTO = null;
        User user = userMapper.getByUserNameAndPassword(request);
        if(user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        loginDTO = new LoginDTO();
        BeanUtils.copyProperties(user, loginDTO);
        
        // 生成token并设置到loginDTO中
        String token = jwtUtils.generateToken(user.getId());
        loginDTO.setToken(token);
        
        return loginDTO;
    }
}
