package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "新增测验请求体")
public class QuizInsertReq {

    @ApiModelProperty(value = "试卷id", notes = "试卷id")
    private Integer examId;

    @ApiModelProperty(value = "用户id", notes = "用户id")
    private Integer uid;

}
