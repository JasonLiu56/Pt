package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "新增视频请求体")
public class VideoInsertReq {

    // 视频名称
    @ApiModelProperty(value = "视频名称", notes = "视频名称")
    private String name;

    // 视频描述
    @ApiModelProperty(value = "视频描述", notes = "视频描述")
    private String description;

    // 章节id
    @ApiModelProperty(value = "章节id", notes = "章节id")
    private Integer chapterId;

}
