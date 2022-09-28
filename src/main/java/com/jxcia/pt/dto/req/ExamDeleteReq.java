package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除考试请求体")
public class ExamDeleteReq {

    @ApiModelProperty(value = "考试id", notes = "考试id")
    private Integer id;

}
