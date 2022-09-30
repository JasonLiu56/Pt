package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "更新选择题请求体")
public class SelectQuestionUpdateReq {

    @ApiModelProperty(value = "选择题id", notes = "选择题id")
    private Integer id;

    @ApiModelProperty(value = "考试id", notes = "考试id")
    private Integer examId;

    @ApiModelProperty(value = "选择题", notes = "选择题")
    private String question;

    @ApiModelProperty(value = "答案A", notes = "答案A")
    private String answerA;

    @ApiModelProperty(value = "答案B", notes = "答案B")
    private String answerB;

    @ApiModelProperty(value = "答案C", notes = "答案C")
    private String answerC;

    @ApiModelProperty(value = "答案D", notes = "答案D")
    private String answerD;

    @ApiModelProperty(value = "正确答案", notes = "正确答案")
    private String answer;

    @ApiModelProperty(value = "答案解析", notes = "答案解析")
    private String analysis;

    @ApiModelProperty(value = "问题分数", notes = "问题分数")
    private Integer score;

}
