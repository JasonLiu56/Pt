package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    // 新增
    Boolean insert(Course course);

    // 通过id判断是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 通过name判断是否存在
    Boolean isExistByName(@Param("name") String name);

    // 判断除开id之外是否存在相同name
    Boolean isExistByNonIdAndName(@Param("id") Integer id, @Param("name") String name);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新
    Boolean update(@Param("id") Integer id, @Param("name") String name, @Param("categoryId") Integer categoryId);

    // 通过id查询
    Course getById(@Param("id") Integer id);

    // 分页查找
    List<Course> getByPage(@Param("name") String name, @Param("categoryId") Integer categoryId);

    // 分页统计总数
    Integer countByPage(@Param("name") String name, @Param("categoryId") Integer categoryId);

}
