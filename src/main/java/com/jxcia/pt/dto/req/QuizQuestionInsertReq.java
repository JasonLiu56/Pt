package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "新增测验题目请求体")
public class QuizQuestionInsertReq {

    @ApiModelProperty(value = "试卷id", notes = "试卷id")
    private Integer examId;

    @ApiModelProperty(value = "测验id", notes = "测验id")
    private Integer quizId;

    @ApiModelProperty(value = "问题id", notes = "问题id")
    private Integer questionId;

    @ApiModelProperty(value = "答案", notes = "答案")
    private String answer;

    @ApiModelProperty(value = "测验题目类型", notes = "填空题:1 判断题:2 选择题:3")
    private Integer questionType;

}
