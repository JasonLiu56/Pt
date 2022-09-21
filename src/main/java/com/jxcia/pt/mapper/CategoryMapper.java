package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    // 新增
    Boolean insert(Category category);

    // 根据id判断分类是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 根据name判断分类是否存在
    Boolean isExistByName(@Param("name") String name);

    // 根据id和name判断是否存在除开自己的同名分类
    Boolean isExistByNonIdAndName(@Param("id") Integer id, @Param("name") String name);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新
    Boolean update(@Param("id") Integer id, @Param("name") String name);

    // 根据id查询
    Category getById(@Param("id") Integer id);

    // 分页查询
    List<Category> getByPage(@Param("name") String name);

    // 分页统计总数
    Integer countByPage(@Param("name") String name);

}
