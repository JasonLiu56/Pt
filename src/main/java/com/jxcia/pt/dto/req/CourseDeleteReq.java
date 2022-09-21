package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除课程请求体")
public class CourseDeleteReq {

    @ApiModelProperty(value = "课程id", notes = "课程id")
    private Integer id;

}
