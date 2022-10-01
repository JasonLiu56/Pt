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
public class FillQuestion implements Serializable {

    public FillQuestion(Integer examId, String question, String answer, String analysis, Integer score) {
        this.question = question;
        this.examId = examId;
        this.answer = answer;
        this.analysis = analysis;
        this.score = score;
    }

    private Integer id;

    private String question;

    private Integer examId;

    private String examName;

    private String answer;

    private String analysis;

    private Integer score;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    private Boolean isDeleted;

}
