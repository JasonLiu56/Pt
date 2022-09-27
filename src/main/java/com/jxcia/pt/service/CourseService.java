package com.jxcia.pt.service;

import com.jxcia.pt.entity.Course;

import java.util.List;

public interface CourseService {

    // 新增
    Boolean insert(String name, Integer categoryId);

    // 根据分类id查看查看是否为空
    Boolean isEmpty(Integer categoryId);

    // 通过id判断是否存在
    Boolean isExist(Integer id);

    // 通过name判断是否存在
    Boolean isExist(String name);

    // 判断除开id之外是否存在相同name
    Boolean isExist(Integer id, String name);

    // 删除
    Boolean delete(Integer id);

    // 修改
    Boolean update(Integer id, String name, Integer categoryId);

    // 通过id获取Course
    Course getById(Integer id);

    // 分页查询Course
    List<Course> getByPage(Integer pageNum, Integer pageSize, String name, Integer categoryId);

    // 分页统计总数
    Integer countByPage(String name, Integer categoryId);

}
