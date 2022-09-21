package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页查询课程请求体")
public class CourseGetByPageReq extends PageReq {

    @ApiModelProperty(value = "课程名称", notes = "课程名称")
    private String name;

    @ApiModelProperty(value = "课程分类id", notes = "课程分类id")
    private Integer categoryId;

}
