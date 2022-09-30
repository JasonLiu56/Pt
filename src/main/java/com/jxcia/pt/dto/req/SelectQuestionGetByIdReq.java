package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "根据id选择题请求体")
public class SelectQuestionGetByIdReq {

    @ApiModelProperty(value = "选择题id", notes = "选择题id")
    private Integer id;

}
