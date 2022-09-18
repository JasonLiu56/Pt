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
@ApiModel(value = "新增用户请求体")
public class UserInsertReq {

    @ApiModelProperty(value = "学工号", notes = "学工号")
    private String staffNo;

    @ApiModelProperty(value = "昵称", notes = "昵称")
    private String nickname;

    @ApiModelProperty(value = "密码", notes = "密码")
    private String password;

    @ApiModelProperty(value = "角色id列表", notes = "角色id列表")
    private List<Integer> roleIds;

}
