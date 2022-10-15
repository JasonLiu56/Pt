package com.jxcia.pt.controller;

import com.jxcia.pt.common.Constant;
import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.ChangePasswordReq;
import com.jxcia.pt.entity.User;
import com.jxcia.pt.service.UserService;
import com.jxcia.pt.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
@Slf4j
@Api(tags = "个人模块", value = "个人模块", description="个人模块接口")
public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ApiOperation(value = "获取个人信息")
    @PostMapping("/info")
    public Result info() throws Exception {
        // 获取uid
        Integer uid = null;
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.isEmpty(userId)) {
            uid = Integer.valueOf(userId);
        }

        // 判断uid是否为空
        if (ObjectUtils.isEmpty(uid)) {
            log.error("请登录");
            return Result.fail("请登录");
        }

        // 检查uid是否存在
        if (!userService.isExist(userId)) {
            log.error("用户不存在 uid:{}", uid);
            return Result.fail("用户不存在");
        }

        // 获取user
        User user = userService.getByUsername(userId);
        user.setPassword(null);
        log.info("获取个人信息 info:{}", user);

        return Result.succ(user);
    }

    @ApiOperation(value = "修改个人密码")
    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePasswordReq changePasswordReq) throws Exception {
        // 获取uid
        Integer uid = null;
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.isEmpty(userId)) {
            uid = Integer.valueOf(userId);
        }

        // 判断uid是否为空
        if (ObjectUtils.isEmpty(uid)) {
            log.error("请登录");
            return Result.fail("请登录");
        }

        // 检查uid是否存在
        if (!userService.isExist(userId)) {
            log.error("用户不存在 uid:{}", uid);
            return Result.fail("用户不存在");
        }

        // 检查参数
        if (StringUtils.isEmpty(changePasswordReq.getPassword())) {
            log.error("修改个人密码参数为空 password:{}", changePasswordReq.getPassword());
            return Result.fail("修改个人密码参数为空");
        }

        // 解码Base64加密的密码
        String password = new String(Base64.decodeBase64(changePasswordReq.getPassword().getBytes()));
        // 加密密码
        String encodedPassword = "{bcrypt}" + passwordEncoder.encode(password);

        // 修改密码
        Boolean flag = userService.changePassword(userId, encodedPassword);
        // 清空redis中的jwt token
        redisUtil.hdel(Constant.USER_KEY, userId);

        if (flag) {
            log.info("修改个人密码成功: {}", changePasswordReq.getPassword());
            return Result.succ("修改个人密码成功", null);
        } else {
            log.error("修改个人密码失败: {}", changePasswordReq.getPassword());
            return Result.fail("修改个人密码失败", null);
        }
    }

}
