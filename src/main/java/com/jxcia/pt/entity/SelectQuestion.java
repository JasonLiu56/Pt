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
public class SelectQuestion implements Serializable {

    public SelectQuestion(Integer examId, String question, String answerA, String answerB, String answerC, String answerD, String answer, String analysis, Integer score) {
        this.examId = examId;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answer = answer;
        this.analysis = analysis;
        this.score = score;
    }

    private Integer id;

    private Integer examId;

    private String examName;

    private String question;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String answer;

    private String analysis;

    private Integer score;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    private Boolean isDeleted;

}
