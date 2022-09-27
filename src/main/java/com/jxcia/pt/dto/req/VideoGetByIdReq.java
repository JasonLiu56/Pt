package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "根据id获取视频请求体")
public class VideoGetByIdReq {

    // 视频名称
    @ApiModelProperty(value = "视频id", notes = "视频id")
    private Integer id;

}
