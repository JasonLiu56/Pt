package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "更新分类请求体")
public class CategoryUpdateReq {

    @ApiModelProperty(value = "分类id", notes = "分类id")
    private Integer id;

    @ApiModelProperty(value = "分类名称", notes = "分类名称")
    private String name;

}
