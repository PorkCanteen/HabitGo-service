package com.example.habitgoservice.mapper;

import com.example.habitgoservice.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("select * from task")
    List<Task> listTask();
}
