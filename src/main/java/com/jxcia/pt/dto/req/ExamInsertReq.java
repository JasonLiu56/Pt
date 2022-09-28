package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "新增考试请求体")
public class ExamInsertReq {

    @ApiModelProperty(value = "考试名称", notes = "考试名称")
    private String name;

    @ApiModelProperty(value = "考试描述", notes = "考试描述")
    private String description;

}
