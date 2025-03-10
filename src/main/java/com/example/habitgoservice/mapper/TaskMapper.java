package com.example.habitgoservice.mapper;

import com.example.habitgoservice.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    // 查询列表
    @Select("select * from task")
    List<Task> listTask();

    // 创建
    @Insert("insert into task(id, name, description, count, isCompleted, taskType, targetType, targetCount) values(#{id}, #{name}, #{description}, #{count}, #{isCompleted}, #{taskType}, #{targetType}, #{targetCount})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addTask(Task task);

    // 修改功能
    @Update("UPDATE task SET "
            + "name = #{name}, "
            + "description = #{description}, "
            + "count = #{count}, "
            + "isCompleted = #{isCompleted}, "
            + "taskType = #{taskType}, "
            + "targetType = #{targetType}, "
            + "targetCount = #{targetCount} "
            + "WHERE id = #{id}")
    int updateTask(Task task);

    // 删除功能
    @Delete("DELETE FROM task WHERE id = #{id}")
    int deleteTask(@Param("id") int id); // 使用@Param明确参数名
}
