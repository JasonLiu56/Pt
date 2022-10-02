package com.jxcia.pt.service;


import com.jxcia.pt.entity.SelectQuestion;

import java.util.List;

public interface SelectQuestionService {

    // 新增
    Boolean insert(Integer examId, String question, String answerA, String answerB, String answerC, String answerD,
                   String answer, String analysis, Integer score);

    // 通过id查看是否存在
    Boolean isExist(Integer id);

    // 通过id和examId查看是否存在
    Boolean isExist(Integer id, Integer examId);

    // 通过examId和question查看是否存在
    Boolean isExist(Integer examId, String question);

    // 通过examId和question查看是否存在(除开自己)
    Boolean isExist(Integer examId, Integer id, String question);

    // 删除
    Boolean delete(Integer id);

    // 更新
    Boolean update(Integer id, Integer examId, String question, String answerA, String answerB, String answerC, String answerD,
                   String answer, String analysis, Integer score);

    // 通过id获取SelectQuestion
    SelectQuestion getById(Integer id);

    // 获取所有的SelectQuestion
    List<SelectQuestion> getAll(Integer examId, String question);

}
