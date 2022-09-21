package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页查询分类请求体")
public class CategoryGetByPageReq extends PageReq {

    @ApiModelProperty(value = "分类名称", notes = "分类名称")
    private String name;

}
