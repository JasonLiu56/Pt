package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "通过id获取判断题请求体")
public class JudgeQuestionGetByIdReq {

    @ApiModelProperty(value = "判断题id", notes = "判断题id")
    private Integer id;

}
