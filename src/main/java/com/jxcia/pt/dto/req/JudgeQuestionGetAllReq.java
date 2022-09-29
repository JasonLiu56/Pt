package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "获取所有判断题请求体")
public class JudgeQuestionGetAllReq {

    @ApiModelProperty(value = "考试id", notes = "考试id")
    private Integer examId;

    @ApiModelProperty(value = "问题", notes = "问题")
    private String question;

}
