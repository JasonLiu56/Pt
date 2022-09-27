package com.jxcia.pt.service;

import com.jxcia.pt.entity.Chapter;

import java.util.List;

public interface ChapterService {

    // 新增
    Boolean insert(String name, Integer courseId);

    // 通过courseId查询是章节是否为空
    Boolean isEmpty(Integer courseId);

    // 通过id查询章节存在
    Boolean isExist(Integer id);

    // 通过同一个courseId下name查询章节是否存在
    Boolean isExist(Integer courseId, String name);

    // 检查章节是否在课程下
    Boolean isExist(Integer courseId, Integer id);

    // 通过同一个course下面name是否已经存在(除开自己)
    Boolean isExist(Integer courseId, Integer id, String name);

    // 删除
    Boolean delete(Integer id);

    // 更新
    Boolean update(Integer id, String name, Integer courseId);

    // 通过id获取章节
    Chapter getById(Integer id);

    // 分页获取章节
    List<Chapter> getAll(String name, Integer courseId);

}
