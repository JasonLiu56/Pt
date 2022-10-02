package com.jxcia.pt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizQuestion implements Serializable {

    public static final Integer fillQuestion = 1;
    public static final Integer judgeQuestion = 2;
    public static final Integer selectQuestion = 3;


    public QuizQuestion(Integer examId, Integer quizId, Integer questionId, String answer, Integer questionType) {
        this.examId = examId;
        this.quizId = quizId;
        this.questionId = questionId;
        this.answer = answer;
        this.questionType = questionType;
    }

    private Integer id;

    private Integer examId;

    private Integer quizId;

    private Integer questionId;

    private String answer;

    private Integer score;

    private Integer questionType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    private Boolean isDeleted;

}
