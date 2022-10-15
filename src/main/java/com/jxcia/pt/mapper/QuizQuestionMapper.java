package com.jxcia.pt.mapper;

import com.jxcia.pt.dto.vo.QuizFillQuestionVo;
import com.jxcia.pt.dto.vo.QuizJudgeQuestionVo;
import com.jxcia.pt.dto.vo.QuizSelectQuestionVo;
import com.jxcia.pt.entity.QuizQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuizQuestionMapper {

    // 新增
    Boolean insert(List<QuizQuestion> quizQuestions);

    // 根据examId查询是否存在
    Boolean isExistByExamId(@Param("examId") Integer examId);

    // 根据quizId查询是否存在
    Boolean isExistByQuizId(@Param("quizId") Integer quizId);

    // 通过questionId和question类型判断是否存在
    Boolean isExistByQuestionId(@Param("questionId") Integer questionId, @Param("questionType") Integer questionType);

    // 通过quizId获取QuizFillQuestion
    List<QuizFillQuestionVo> getAllFillQuestion(@Param("quizId") Integer quizId);

    // 通过quizId获取QuizQuestion
    List<QuizJudgeQuestionVo> getAllJudgeQuestion(@Param("quizId") Integer quizId);

    // 通过quizId获取QuizQuestion
    List<QuizSelectQuestionVo> getAllSelectQuestion(@Param("quizId") Integer quizId);

}
