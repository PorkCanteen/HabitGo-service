package com.example.habitgoservice.mapper;

import com.example.habitgoservice.entity.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {
    // 查询列表
    @Select("select * from todo")
    List<Todo> listTodo();

    // 创建
    @Insert("insert into todo(id, name, description, finishDate, isFinished) values(#{id}, #{name}, #{description}, #{finishDate}, #{isFinished})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addTodo(Todo todo);

    // 修改功能
    @Update("UPDATE todo SET "
            + "name = #{name}, "
            + "description = #{description}, "
            + "finishDate = #{finishDate}, "
            + "isFinished = #{isFinished} "
            + "WHERE id = #{id}")
    int updateTodo(Todo todo);

    // 删除功能
    @Delete("DELETE FROM todo WHERE id = #{id}")
    int deleteTodo(@Param("id") int id); // 使用@Param明确参数名
}
