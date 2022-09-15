package com.jxcia.pt.dto.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "验证码响应体")
public class CaptchaVo {

    @ApiModelProperty(value = "验证码对应key", notes = "验证码对应key")
    private String userKey;

    @ApiModelProperty(value = "验证码base64字符串", notes = "验证码base64字符串")
    private String captchaImg;

}
