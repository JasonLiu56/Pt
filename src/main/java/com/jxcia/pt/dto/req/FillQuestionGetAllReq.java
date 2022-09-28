package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "获取所有填空题请求体")
public class FillQuestionGetAllReq {

    @ApiModelProperty(value = "试卷id", notes = "试卷id")
    private Integer examId;

    @ApiModelProperty(value = "填空题", notes = "填空题")
    private String question;

}
