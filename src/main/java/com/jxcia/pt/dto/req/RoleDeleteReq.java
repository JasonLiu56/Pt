package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除角色请求体")
public class RoleDeleteReq {

    @ApiModelProperty(value = "角色id", notes = "角色id")
    private Integer id;

}
