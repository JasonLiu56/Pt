package com.jxcia.pt.service;

import com.jxcia.pt.dto.vo.QuizFillQuestionVo;
import com.jxcia.pt.dto.vo.QuizJudgeQuestionVo;
import com.jxcia.pt.dto.vo.QuizSelectQuestionVo;
import com.jxcia.pt.entity.QuizQuestion;

import java.util.List;

public interface QuizQuestionService {

    // 新增
    Boolean insert(List<QuizQuestion> quizQuestions);

    // 通过quizId获取QuizFillQuestion
    List<QuizFillQuestionVo> getAllFillQuestion(Integer quizId);

    // 通过quizId获取QuizQuestion
    List<QuizJudgeQuestionVo> getAllJudgeQuestion(Integer quizId);

    // 通过quizId获取QuizQuestion
    List<QuizSelectQuestionVo> getAllSelectQuestion(Integer quizId);

}
