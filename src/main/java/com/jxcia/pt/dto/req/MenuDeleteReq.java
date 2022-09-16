package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除菜单请求体")
public class MenuDeleteReq {

    @ApiModelProperty(value = "菜单id列表", notes = "菜单id列表")
    private Integer id;

}
