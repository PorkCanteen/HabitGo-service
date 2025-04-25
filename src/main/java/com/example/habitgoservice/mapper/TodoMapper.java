package com.example.habitgoservice.mapper;

import com.example.habitgoservice.entity.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {
    // 查询列表
    @Select("select * from todo where user_id = #{userId} order by isFinished asc")
    List<Todo> listTodo(@Param("userId") int userId);

    // 创建
    @Insert("insert into todo(id, user_id, name, description, finishDate, isFinished, type, createTime, updateTime) " +
            "values(#{id}, #{userId}, #{name}, #{description}, #{finishDate}, #{isFinished}, #{type}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addTodo(Todo todo);

    // 修改功能
    @Update("UPDATE todo SET "
            + "name = #{name}, "
            + "description = #{description}, "
            + "finishDate = #{finishDate}, "
            + "isFinished = #{isFinished}, "
            + "type = #{type}, "
            + "updateTime = #{updateTime} "
            + "WHERE id = #{id} AND user_id = #{userId}")
    int updateTodo(Todo todo);

    // 删除功能
    @Delete("DELETE FROM todo WHERE id = #{id} AND user_id = #{userId}")
    int deleteTodo(@Param("id") int id, @Param("userId") int userId);

    // 切换完成状态
    @Update("UPDATE todo SET isFinished = NOT isFinished, updateTime = NOW() WHERE id = #{id} AND user_id = #{userId}")
    int toggleComplete(@Param("id") int id, @Param("userId") int userId);
}
