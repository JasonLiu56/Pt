package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "更新菜单请求体")
public class MenuUpdateReq {

    @ApiModelProperty(value = "菜单id", notes = "角色id")
    private Integer id;

    @ApiModelProperty(value = "菜单路由", notes = "菜单路由")
    private String pattern;

    @ApiModelProperty(value = "角色id列表", notes = "角色id列表")
    private List<Integer> roleIds;

}
