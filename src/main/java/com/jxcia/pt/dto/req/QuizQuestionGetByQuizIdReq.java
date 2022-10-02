package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "通过quizId获取测验题目请求体")
public class QuizQuestionGetByQuizIdReq {

    @ApiModelProperty(value = "测验id", notes = "测验id")
    private Integer quizId;

    @ApiModelProperty(value = "用户id", notes = "用户id")
    private Integer uid;

}
