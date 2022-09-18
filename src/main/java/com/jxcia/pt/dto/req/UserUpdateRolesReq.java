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
@ApiModel(value = "更新用户角色请求体")
public class UserUpdateRolesReq {

    // 用户id
    @ApiModelProperty(value = "用户id", notes = "用户id")
    private Integer id;

    // 角色id列表
    @ApiModelProperty(value = "角色id列表", notes = "角色id列表")
    private List<Integer> roleIds;

}
