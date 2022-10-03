package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "教师获取所有测验请求体")
public class QuizGetByPageTeacherReq extends PageReq {

    @ApiModelProperty(value = "用户id", notes = "用户id")
    private Integer uid;

    @ApiModelProperty(value = "测验名称", notes = "测验名称")
    private String examName;

}
