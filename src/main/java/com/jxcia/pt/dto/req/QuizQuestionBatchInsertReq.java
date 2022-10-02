package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "批量新增测验题目请求体")
public class QuizQuestionBatchInsertReq {

    @ApiModelProperty(value = "新增测验题目请求体", notes = "新增测验题目请求体")
    List<QuizQuestionInsertReq> quizQuestionInsertReqs;

    @ApiModelProperty(value = "用户id", notes = "用户id")
    private Integer uid;

}
