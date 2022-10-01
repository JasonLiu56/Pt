package com.jxcia.pt.service;

import com.jxcia.pt.entity.Quiz;

import java.util.List;

public interface QuizService {

    // 新增
    Boolean insert(Integer examId, Integer uid);

    // 根据id判断是否存在
    Boolean isExist(Integer id);

    // 根据examId和uid判断是否存在
    Boolean isExist(Integer id, Integer uid);

    // 删除
    Boolean delete(Integer id, Integer uid);

    // 根据id获取Quiz
    Quiz getById(Integer id, Integer uid);

    // 分页查询
    List<Quiz> getByPage(Integer pageNum, Integer pageSize, Integer uid, String examName);

    // 分页统计总数
    Integer countByPage(Integer uid, String examName);

}
