package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "id获取角色请求体")
public class RoleGetByIdReq {

    @ApiModelProperty(value = "角色id", notes = "角色id")
    private Integer id;

}
