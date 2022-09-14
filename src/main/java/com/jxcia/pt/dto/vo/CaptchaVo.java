package com.jxcia.pt.dto.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaVo {

    private String userKey;

    private String captchaImg;

}
