package com.example.habitgoservice.mapper;

import com.example.habitgoservice.controller.request.LoginRequest;
import com.example.habitgoservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> listUser();

    @Select("select * from user where username = #{username} and password = #{password}")
    User getByUserNameAndPassword(LoginRequest request);
}
