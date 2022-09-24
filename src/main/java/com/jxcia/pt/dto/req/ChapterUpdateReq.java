package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "更新章节请求体")
public class ChapterUpdateReq {

    @ApiModelProperty(value = "章节id", notes = "章节id")
    private Integer id;

    @ApiModelProperty(value = "课程id", notes = "课程id")
    private Integer courseId;

    @ApiModelProperty(value = "章节名称", notes = "章节名称")
    private String name;

}
