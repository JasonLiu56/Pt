package com.jxcia.pt.service;

import com.jxcia.pt.entity.JudgeQuestion;

import java.util.List;

public interface JudgeQuestionService {

    // 新增
    Boolean insert(Integer examId, String question, String answer, String analysis, Integer score);

    // 通过id查询是否存在
    Boolean isExist(Integer id);

    // 通过examId和question查询是否存在
    Boolean isExist(Integer examId, String question);

    // 通过examId和question查询是否存在(除开自己)
    Boolean isExist(Integer examId, Integer id, String question);

    // 删除
    Boolean delete(Integer id);

    // 更新
    Boolean update(Integer id, Integer examId, String question, String answer, String analysis, Integer score);

    // 通过id获取判断题
    JudgeQuestion getById(Integer id);

    // 获取所有
    List<JudgeQuestion> getAll(Integer examId, String question);

}
