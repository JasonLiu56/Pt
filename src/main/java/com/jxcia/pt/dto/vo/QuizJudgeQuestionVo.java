package com.jxcia.pt.dto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizJudgeQuestionVo {

    private Integer id;

    private Integer examId;

    private Integer quizId;

    private Integer questionId;

    private String answer;

    private String question;

    private String rightAnswer;

    private String analysis;

    private Integer score;

    private Integer srcScore;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    private Boolean isDeleted;

}
