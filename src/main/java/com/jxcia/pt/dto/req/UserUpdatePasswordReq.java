package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "更新用户密码请求体")
public class UserUpdatePasswordReq {

    @ApiModelProperty(value = "用户id", notes = "用户id")
    private Integer id;

    @ApiModelProperty(value = "密码", notes = "密码")
    private String password;

}
