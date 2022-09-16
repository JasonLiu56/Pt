package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "通过id获取菜单请求体")
public class MenuGetByIdReq {

    @ApiModelProperty(value = "菜单id", notes = "菜单id")
    private Integer id;

}
