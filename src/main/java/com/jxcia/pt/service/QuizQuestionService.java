package com.jxcia.pt.service;

import com.jxcia.pt.dto.vo.QuizFillQuestionVo;
import com.jxcia.pt.dto.vo.QuizJudgeQuestionVo;
import com.jxcia.pt.dto.vo.QuizSelectQuestionVo;
import com.jxcia.pt.entity.QuizQuestion;

import java.util.List;

public interface QuizQuestionService {

    // 新增
    Boolean insert(List<QuizQuestion> quizQuestions);

    // 通过examId判断是否为空
    Boolean isEmpty(Integer examId);

    // 通过quiz判断是否存在
    Boolean isExistByQuizId(Integer quizId);

    // 通过fillQuestion的id判断是否存在
    Boolean isExistByFillQuestionId(Integer fillQuestionId);

    // 通过judgeQuestion的id判断是否存在
    Boolean isExistByJudgeQuestionId(Integer judgeQuestionId);

    // 通过selectQuestion的id判断是否存在
    Boolean isExistBySelectQuestionId(Integer selectQuestionId);

    // 通过quizId获取QuizFillQuestion
    List<QuizFillQuestionVo> getAllFillQuestion(Integer quizId);

    // 通过quizId获取QuizQuestion
    List<QuizJudgeQuestionVo> getAllJudgeQuestion(Integer quizId);

    // 通过quizId获取QuizQuestion
    List<QuizSelectQuestionVo> getAllSelectQuestion(Integer quizId);

}
