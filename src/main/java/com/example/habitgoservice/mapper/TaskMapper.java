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
    @Insert("insert into task(id, name, description, count, isCompleted, taskType, targetType, targetCount, createTime, updateTime) " +
            "values(#{id}, #{name}, #{description}, #{count}, #{isCompleted}, #{taskType}, #{targetType}, #{targetCount}, #{createTime}, #{updateTime})")
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
            + "targetCount = #{targetCount}, "
            + "updateTime = #{updateTime} "
            + "WHERE id = #{id}")
    int updateTask(Task task);

    // 删除功能
    @Delete("DELETE FROM task WHERE id = #{id}")
    int deleteTask(@Param("id") int id); // 使用@Param明确参数名

    // 切换任务状态
    @Update("UPDATE task SET "
            + "isCompleted = CASE WHEN isCompleted = 1 THEN 0 ELSE 1 END, "
            + "count = CASE WHEN isCompleted = 0 THEN count - 1 ELSE count + 1 END, "
            + "updateTime = NOW() "
            + "WHERE id = #{id}")
    int toggleTaskStatus(@Param("id") int id);

    // 重置所有任务状态
    @Update("UPDATE task SET isCompleted = 0, updateTime = NOW()")
    int resetAllTaskStatus();

    // 获取所有已完成的任务
    @Select("SELECT * FROM task WHERE iscompleted = 1")
    List<Task> getCompletedTasks();

    // 更新任务的完成日期
    @Update("UPDATE task SET completeddates = #{completedDates}, updateTime = NOW() WHERE id = #{id}")
    void updateCompletedDates(Task task);
}
