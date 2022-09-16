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
@ApiModel(value = "新增菜单请求体")
public class MenuInsertReq {

    // 访问路由
    @ApiModelProperty(value = "访问路由", notes = "访问路由")
    private String pattern;

    // 角色id列表
    @ApiModelProperty(value = "角色id列表", notes = "角色id列表")
    private List<Integer> roleIds;

}
