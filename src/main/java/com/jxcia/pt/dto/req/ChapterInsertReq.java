package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "新增章节请求体")
public class ChapterInsertReq {

    @ApiModelProperty(value = "章节名称", notes = "章节名称")
    private String name;

    @ApiModelProperty(value = "课程id", notes = "课程id")
    private Integer courseId;

}
