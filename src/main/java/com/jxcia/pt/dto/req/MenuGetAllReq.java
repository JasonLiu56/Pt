package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "查询所有菜单请求体")
public class MenuGetAllReq {

    @ApiModelProperty(value = "菜单路由", notes = "菜单路由")
    private String pattern;

}
