package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "更新课程请求体")
public class CourseUpdateReq {

    @ApiModelProperty(value = "课程id", notes = "课程id")
    private Integer id;

    @ApiModelProperty(value = "课程名称", notes = "课程名称")
    private String name;

    @ApiModelProperty(value = "课程分类id", notes = "课程分类id")
    private Integer categoryId;

}
