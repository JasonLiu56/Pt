package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除已分类请求体")
public class CategoryDeleteReq {

    @ApiModelProperty(value = "分类id", notes = "分类id")
    private Integer id;

}
