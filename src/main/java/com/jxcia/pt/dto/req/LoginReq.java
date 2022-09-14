package com.jxcia.pt.dto.req;

import lombok.Data;

@Data
public class LoginReq {

    private String username;    // 工号

    private String password;    // 密码

    private String code;    // 验证码

    private String userKey; // 验证码对应的key

}
