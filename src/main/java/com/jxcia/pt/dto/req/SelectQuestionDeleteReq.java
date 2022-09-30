package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除选择题请求体")
public class SelectQuestionDeleteReq {

    @ApiModelProperty(value = "选择题id", notes = "选择题id")
    private Integer id;

}
