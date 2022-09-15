package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录请求体")
public class LoginReq {

    @ApiModelProperty(value = "工号", notes = "工号(唯一性)")
    private String username;    // 工号

    @ApiModelProperty(value = "=密码", notes = "密码")
    private String password;    // 密码

    @ApiModelProperty(value = "验证码", notes = "验证码")
    private String code;    // 验证码

    @ApiModelProperty(value = "验证码key", notes = "验证码对应的key")
    private String userKey; // 验证码对应的key

}
