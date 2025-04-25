package com.example.habitgoservice.mapper;

import com.example.habitgoservice.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    // 查询列表
    @Select("select * from task where user_id = #{userId}")
    List<Task> listTask(@Param("userId") int userId);

    // 创建
    @Insert("insert into task(id, user_id, name, description, count, isCompleted, taskType, targetType, targetCount, createTime, updateTime) " +
            "values(#{id}, #{userId}, #{name}, #{description}, #{count}, #{isCompleted}, #{taskType}, #{targetType}, #{targetCount}, #{createTime}, #{updateTime})")
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
            + "WHERE id = #{id} AND user_id = #{userId}")
    int updateTask(Task task);

    // 删除功能
    @Delete("DELETE FROM task WHERE id = #{id} AND user_id = #{userId}")
    int deleteTask(@Param("id") int id, @Param("userId") int userId);

    // 切换任务状态
    @Update("UPDATE task SET "
            + "isCompleted = CASE WHEN isCompleted = 1 THEN 0 ELSE 1 END, "
            + "count = CASE WHEN isCompleted = 0 THEN count - 1 ELSE count + 1 END, "
            + "updateTime = NOW() "
            + "WHERE id = #{id} AND user_id = #{userId}")
    int toggleTaskStatus(@Param("id") int id, @Param("userId") int userId);

    // 重置所有任务状态
    @Update("UPDATE task SET isCompleted = 0, updateTime = NOW() WHERE user_id = #{userId}")
    int resetAllTaskStatus(@Param("userId") int userId);

    // 获取所有已完成的任务
    @Select("SELECT * FROM task WHERE iscompleted = 1 AND user_id = #{userId}")
    List<Task> getCompletedTasks(@Param("userId") int userId);

    // 更新任务的完成日期
    @Update("UPDATE task SET completeddates = #{completedDates}, updateTime = NOW() WHERE id = #{id} AND user_id = #{userId}")
    void updateCompletedDates(Task task);
}
