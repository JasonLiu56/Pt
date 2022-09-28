package com.jxcia.pt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectQuestion {

    private Integer id;

    private Integer examId;

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
