package com.jxcia.pt.service;


import com.jxcia.pt.entity.FillQuestion;

import java.util.List;

public interface FillQuestionService {

    // 新增
    Boolean insert(Integer examId, String question, String answer, String analysis, Integer score);

    // 根据id查看FillQuestion是否存在
    Boolean isExist(Integer id);

    // 根据examId和id查找是否存在
    Boolean isExist(Integer id, Integer examId);

    // 根据examId和question查看FillQuestion是否存在
    Boolean isExist(Integer examId, String question);

    // 根据examId和question查看FillQuestion是否存在(除开自己本身)
    Boolean isExist(Integer id, Integer examId, String question);

    // 删除
    Boolean delete(Integer id);

    // 修改
    Boolean update(Integer id, Integer examId, String question, String answer, String analysis, Integer score);

    // 根据id获取FillQuestion
    FillQuestion getById(Integer id);

    // 查询所有FillQuestion
    List<FillQuestion> getAll(Integer examId, String question);

}
