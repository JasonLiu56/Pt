package com.jxcia.pt.service;

import com.jxcia.pt.entity.Category;

import java.util.List;

public interface CategoryService {

    // 新增
    Boolean insert(String name);

    // 根据id查看是否存在
    Boolean isExist(Integer id);

    // 根据name查看是否存在
    Boolean isExist(String name);

    // 根据id和name判断是否存在除开自己的同名分类
    Boolean isExist(Integer id, String name);

    // 删除
    Boolean delete(Integer id);

    // 更新
    Boolean update(Integer id, String name);

    // 通过id查询分类
    Category getById(Integer id);

    // 分页查询
    List<Category> getByPage(Integer pageNum, Integer pageSize, String name);

    // 统计分页总数
    Integer countByPage(String name);

}
