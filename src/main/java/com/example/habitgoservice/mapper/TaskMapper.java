package com.example.habitgoservice.mapper;

import com.example.habitgoservice.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("select * from task")
    List<Task> listTask();
    @Insert("insert into task(id, name, description, count, isCompleted, taskType, targetType, targetCount) values(#{id}, #{name}, #{description}, #{count}, #{isCompleted}, #{taskType}, #{targetType}, #{targetCount})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addTask(Task task);
}
