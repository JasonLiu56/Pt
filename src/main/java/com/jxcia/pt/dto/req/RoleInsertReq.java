package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "新增角色请求体")
public class RoleInsertReq {

    @ApiModelProperty(value = "角色名称", notes = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色中文名称", notes = "角色中文名称")
    private String nameZh;

}
