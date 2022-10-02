package com.jxcia.pt.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestionVo {

    private List<QuizFillQuestionVo> quizFillQuestions;

    private List<QuizJudgeQuestionVo> quizJudgeQuestions;

    private List<QuizSelectQuestionVo> quizSelectQuestions;

}
