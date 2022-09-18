package com.jxcia.pt.dto.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页查询用户请求体")
public class UserGetPageReq extends PageReq {

    @ApiModelProperty(value = "用户昵称", notes = "用户昵称")
    private String nickname;

}
