package com.example.habitgoservice.mapper;

import com.example.habitgoservice.entity.Todo;
import com.example.habitgoservice.entity.TodoChild;
import com.example.habitgoservice.config.JsonTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {
    // 查询列表
    @Select("select *, user_id as userId, COALESCE(children, '[]') as children from todo where user_id = #{userId} order by isFinished asc")
    @Results({
        @Result(property = "children", column = "children", typeHandler = JsonTypeHandler.class)
    })
    List<Todo> listTodo(@Param("userId") int userId);

    // 查询单个详情
    @Select("select *, user_id as userId, COALESCE(children, '[]') as children from todo where id = #{id} and user_id = #{userId}")
    @Results({
        @Result(property = "children", column = "children", typeHandler = JsonTypeHandler.class)
    })
    Todo getTodoById(@Param("id") int id, @Param("userId") int userId);

    // 创建
    @Insert("insert into todo(id, user_id, name, description, finishDate, isFinished, type, createTime, updateTime, children) " +
            "values(#{id}, #{userId}, #{name}, #{description}, #{finishDate}, #{isFinished}, #{type}, #{createTime}, #{updateTime}, #{children, typeHandler=com.example.habitgoservice.config.JsonTypeHandler})")
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

    // 更新children字段
    @Update("UPDATE todo SET children = #{children, typeHandler=com.example.habitgoservice.config.JsonTypeHandler}, updateTime = #{updateTime} WHERE id = #{id} AND user_id = #{userId}")
    int updateTodoChildren(@Param("id") int id, @Param("userId") int userId, @Param("children") List<TodoChild> children, @Param("updateTime") java.time.LocalDateTime updateTime);

    // 删除功能
    @Delete("DELETE FROM todo WHERE id = #{id} AND user_id = #{userId}")
    int deleteTodo(@Param("id") int id, @Param("userId") int userId);

    // 切换完成状态
    @Update("UPDATE todo SET isFinished = NOT isFinished WHERE id = #{id} AND user_id = #{userId}")
    int toggleComplete(@Param("id") int id, @Param("userId") int userId);
}
