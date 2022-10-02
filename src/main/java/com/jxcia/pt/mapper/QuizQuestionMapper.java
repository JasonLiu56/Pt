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

    // 通过quizId获取QuizFillQuestion
    List<QuizFillQuestionVo> getAllFillQuestion(@Param("quizId") Integer quizId);

    // 通过quizId获取QuizQuestion
    List<QuizJudgeQuestionVo> getAllJudgeQuestion(@Param("quizId") Integer quizId);

    // 通过quizId获取QuizQuestion
    List<QuizSelectQuestionVo> getAllSelectQuestion(@Param("quizId") Integer quizId);

}
