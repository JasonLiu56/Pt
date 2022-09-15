package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "获取所有角色请求体")
public class RoleGetAllReq {

    @ApiModelProperty(value = "角色名称", notes = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色中文名称", notes = "角色中文名称")
    private String nameZh;

}
