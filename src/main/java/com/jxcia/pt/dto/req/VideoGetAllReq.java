package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "获取所有视频请求体")
public class VideoGetAllReq {

    @ApiModelProperty(value = "章节id", notes = "章节id")
    private Integer chapterId;

    @ApiModelProperty(value = "章节名称", notes = "章节名称")
    private String name;

}
