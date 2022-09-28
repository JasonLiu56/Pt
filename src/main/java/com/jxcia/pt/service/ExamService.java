package com.jxcia.pt.service;

import com.jxcia.pt.entity.Exam;

import java.util.List;

public interface ExamService {

    // 新增
    Boolean insert(String name, String description);

    // 根据id查询是否存在
    Boolean isExist(Integer id);

    // 根据name查询是否存在
    Boolean isExist(String name);

    // 根据name查询是否存在(除开自己)
    Boolean isExist(Integer id, String name);

    // 删除
    Boolean delete(Integer id);

    // 更新
    Boolean update(Integer id, String name, String description);

    // 通过id获取Exam
    Exam getById(Integer id);

    // 分页查询
    List<Exam> getByPage(Integer pageNum, Integer pageSize, String name, String description);

    // 统计分页查询总数
    Integer countByPage(String name, String description);

}
