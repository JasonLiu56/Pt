package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "更新用户昵称请求体")
public class UserUpdateNicknameReq {

    @ApiModelProperty(value = "用户id", notes = "用户id")
    private Integer id;

    @ApiModelProperty(value = "用户昵称", notes = "用户昵称")
    private String nickname;

}
