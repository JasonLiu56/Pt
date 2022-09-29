package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "新增判断题请求体")
public class JudgeQuestionInsertReq {

    @ApiModelProperty(value = "考试id", notes = "考试id")
    private Integer examId;

    @ApiModelProperty(value = "问题", notes = "问题")
    private String question;

    @ApiModelProperty(value = "答案", notes = "答案")
    private String answer;

    @ApiModelProperty(value = "答案解析", notes = "答案解析")
    private String analysis;

    @ApiModelProperty(value = "问题分数", notes = "问题分数")
    private Integer score;

}
